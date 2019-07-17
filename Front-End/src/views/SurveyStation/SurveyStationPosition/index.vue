<template>
  <div>
    <div style="padding:10px;">
      <el-row :gutter="8">
        <el-col :xs="{span: 24}" :sm="{span: 12}" :md="{span: 12}" :lg="{span: 6}" :xl="{span: 6}" style=";width: 1000px">
          <baidu-map class="map" ak="2KAyaDuGxQlnfX7lA5pD4fYpzzoH21zB" :scroll-wheel-zoom="false"  :zoom="12" center="杭州">
            <bm-navigation anchor="BMAP_ANCHOR_TOP_RIGHT"></bm-navigation>
            <bm-map-type :map-types="['BMAP_NORMAL_MAP', 'BMAP_HYBRID_MAP']" anchor="BMAP_ANCHOR_TOP_LEFT"></bm-map-type>
            <!--<bm-marker v-for="maker in tableData" v-bind:key="maker" :position="{lng : maker.lgtd, lat: maker.lttd}" @click="clickHandler" >-->
            <!--</bm-marker>-->
            <bm-point-collection :points="points"  color="red" shape="BMAP_POINT_SHAPE_STAR" size="BMAP_POINT_SIZE_BIGGER" @click="clickHandler"></bm-point-collection>
          </baidu-map>
        </el-col>
        <el-col :xs="{span: 24}" :sm="{span: 12}" :md="{span: 12}" :lg="{span: 6}" :xl="{span: 6}" style="width: 400px;padding-left: 20px">
          <!--（2）测站监控降雨量信息-->
          <!---->
          <!---->
          <!---->
          <el-table
            border
            :data="Data2"
            style="width: 100%; margin-top: 20px"
            height="235"
            v-if="isDisplay">
            <el-table-column
              fixed
              prop="stcd"
              label="监站编号"
              width="150">
            </el-table-column>
            <el-table-column
              prop="tm"
              label="监控时间"
              width="120">
            </el-table-column>
            <el-table-column
              prop="val"
              label="降雨量（月）"
              width="120">
            </el-table-column>
          </el-table>
          <el-table
            border
            :data="tableData3"
            style="width: 100%;margin-top: 20px"
            height="235"
            v-if="isDisplay">
            <el-table-column
              fixed
              prop="stcd"
              label="监站编号"
              width="150">
            </el-table-column>
            <el-table-column
              prop="tm"
              label="监控时间"
              width="120">
            </el-table-column>
            <el-table-column
              prop="z"
              label="平均水位(月）"
              width="120">
            </el-table-column>
          </el-table>
          <!--（4）测站监控水库信息-->
          <!---->
          <!---->
          <!---->
          <el-table
            border
            :data="tableData4"
            style="width: 100%;margin-top: 20px"
            height="235"
            v-if="isDisplay">
            <el-table-column
              fixed
              prop="stcd"
              label="监站编号"
              width="150">
            </el-table-column>
            <el-table-column
              prop="tm"
              label="监控时间"
              width="120">
            </el-table-column>
            <el-table-column
              prop="rz"
              label="库水位(月）"
              width="120">
            </el-table-column>
          </el-table>
        </el-col>
      </el-row>
    </div>
    <!--<div v-for="maker in tableData" v-bind:key="maker">{{maker.lgtd}}-&#45;&#45;{{maker.lttd}}</div>-->
  </div>
</template>
<script>
// import { BmlMarkerClusterer } from 'vue-baidu-map'
export default {
  components: {
  },
  data() {
    return {
      markers: [],
      tableData: [],
      points: [],
      tableData2: [],
      ostcd: '',
      isDisplay: false,
      Data: [],
      Data2: [],
      tableData3: [],
      tableData4: [],
      input: ''
    }
  },
  mounted() {
    const points = []
    this.$axios
      .get('http://localhost:1313/surveyStation/findSurveyStation')
      .then((response) => {
        // console.log(response.data)
        this.tableData = []
        for (var j in response.data) {
          this.tableData.push(response.data[j])
          // console.log(this.tableData)
          // console.log(this.tableData.lttd)
          const position = { lng: response.data[j].lgtd, lat: response.data[j].lttd }
          points.push(position)
        }
      })
      .catch(function(error) { // 请求失败处理
        console.log(error)
      })
    this.points = points
  },
  methods: {
    clickHandler(e) {
      this.$axios
        .get('http://localhost:1313/surveyStation/getSurveyStationByCoord/' + e.point.lng + '/' + e.point.lat)
        .then((response) => {
          this.tableData2 = []
          // console.log(response.data)
          for (var i in response.data) {
            this.tableData2.push(response.data[i])
          }
        })
        .catch(function(error) { // 请求失败处理
          console.log(error)
        })
      alert('监站编号:' + this.tableData2[0].stcd + '\n站名:' + this.tableData2[0].stnm + '\n地址:' + this.tableData2[0].stlc + '\n经度:' + this.tableData2[0].lgtd + '\n纬度:' + this.tableData2[0].lttd)
      // this.$router.push({ name: 'surveyss', params: { ostcd: this.tableData2[0].stcd }})
      this.isDisplay = true
      this.input = this.tableData2[0].stcd
      this.search()
    },
    search() {
      this.$axios
        .get('http://localhost:1313/surveyStation/findSurveyStationByStcd/' + this.input) // 根据input提取基本信息
        .then((response) => {
          this.isDisplay = true
          this.Data = []
          for (var j in response.data) {
            this.Data.push(response.data[j])
          }
          // 根据input提取降雨量（2）
          // //
          // //
          this.$axios
            .get('http://localhost:1313/precipitation/getPrpByStcd/' + this.input)
            .then((response2) => {
              this.isDisplay = true
              this.Data2 = []
              for (var j in response2.data) {
                var xxx = { stcd: response2.data[j][0],
                  tm: response2.data[j][1],
                  val: response2.data[j][2] }
                this.Data2.push(xxx)
              }
            })
            .catch(function(error) { // 请求失败处理
              console.log(error)
            })
          // 根据input提取河道信息（3）
          // //
          // //
          this.$axios
            .get('http://localhost:1313/river/findRiverByStcd/' + this.input)
            .then((response3) => {
              this.isDisplay = true
              this.tableData3 = []
              for (var j in response3.data) {
                var xxx = { stcd: response3.data[j][0],
                  tm: response3.data[j][1],
                  z: response3.data[j][2] }
                this.tableData3.push(xxx)
              }
            })
            .catch(function(error) { // 请求失败处理
              console.log(error)
            })
          // 根据input提取水库信息（4）
          // //
          // //
          this.$axios
            .get('http://localhost:1313/reservoir/findRsvrByStcd/' + this.input)
            .then((response4) => {
              this.isDisplay = true
              this.tableData4 = []
              for (var j in response4.data) {
                var xxx = { stcd: response4.data[j][0],
                  tm: response4.data[j][1],
                  rz: response4.data[j][2] }
                this.tableData4.push(xxx)
              }
            })
            .catch(function(error) { // 请求失败处理
              console.log(error)
            })
          this.input = ''
        })
        .catch(function(error) { // 请求失败处理
          console.log(error)
        })
    }
  }
}
</script>
<style lang="scss" scoped>
  .block
  {
    display: flex;
    flex-flow: nowrap;
    justify-content: flex-start;
  }
  .map{
    width: 900px;
    height:750px;
    margin-left: 10px;
    margin-top: 10px;
  }
</style>
