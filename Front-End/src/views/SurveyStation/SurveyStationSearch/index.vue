<template>
  <div style="padding:30px;">
    <div class="block">
      <!--<el-input-->
        <!--placeholder="请输入监控点编号"-->
        <!--v-model="input"-->
        <!--clearable-->
        <!--style="width: 200px"-->
        <!--&gt;-->
      <!--</el-input>-->
      <el-autocomplete
        class="inline-input"
        v-model="state"
        :fetch-suggestions="querySearch"
        placeholder="请输入监站编号"
        :trigger-on-focus="false"
        @select="handleSelect"
        style="width: 200px; margin-left: 20px"
        clearable
      >
        <template slot-scope="{ item }">
          <div class="name">{{ item.warn }}</div>
          <div class="name" style="color:green">{{ item.value=item.stnm }}</div>
        </template>
      </el-autocomplete>
      <el-button type="primary" icon="el-icon-search" style="margin-left: 30px;" @click="search">搜索</el-button>
    </div>
    <!--（1）测站基本信息-->
    <!---->
    <!---->
    <!---->
    <el-table
    :data="tableData"
    style="width: 100%"
    height="105"
    v-if="isDisplay">
    <el-table-column
      fixed
      prop="stcd"
      label="监站编号"
      width="150">
    </el-table-column>
    <el-table-column
      prop="stnm"
      label="监站名称"
      width="120">
    </el-table-column>
    <el-table-column
      prop="stlc"
      label="监站地址"
      width="120">
    </el-table-column>
    <el-table-column
      prop="lgtd"
      label="经度"
      width="120">
    </el-table-column>
    <el-table-column
      prop="lttd"
      label="经度"
      width="120">
    </el-table-column>
    <el-table-column
      prop="rvnm"
      label="监控的河流"
      width="120">
    </el-table-column>
    <el-table-column
      prop="hnnm"
      label="监控的水系"
      width="120">
    </el-table-column>
    <el-table-column
      prop="bsnm"
      label="监控的流域"
      width="120">
    </el-table-column>
    <el-table-column
      prop="addvcd"
      label="所在区域"
      width="120">
    </el-table-column>
    <el-table-column
      prop="admauth"
      label="主管单位"
      width="120">
    </el-table-column>
    <el-table-column
      prop="sttp"
      label="站类"
      width="120">
    </el-table-column>
  </el-table>
    <el-row :gutter="8">
      <el-col :xs="{span: 24}" :sm="{span: 12}" :md="{span: 12}" :lg="{span: 6}" :xl="{span: 6}" style=";width: 395px">
        <!--（2）测站监控降雨量信息-->
        <!---->
        <!---->
        <!---->
        <el-table
          border
          :data="tableData2"
          style="width: 100%; margin-top: 20px"
          height="300"
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
      </el-col>
      <el-col :xs="{span: 24}" :sm="{span: 12}" :md="{span: 12}" :lg="{span: 6}" :xl="{span: 6}" style="margin-left: 20px;width: 395px">
        <!--（3）测站监控河道信息-->
        <!---->
        <!---->
        <!---->
        <el-table
          border
          :data="tableData3"
          style="width: 100%;margin-top: 20px"
          height="300"
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
      </el-col>
      <el-col :xs="{span: 24}" :sm="{span: 12}" :md="{span: 12}" :lg="{span: 6}" :xl="{span: 6}" style="margin-left: 20px;width: 395px">
        <!--（4）测站监控水库信息-->
        <!---->
        <!---->
        <!---->
        <el-table
          border
          :data="tableData4"
          style="width: 100%;margin-top: 20px"
          height="300"
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
</template>

<script>
export default {
  data() {
    return {
      value1: '',
      value2: '',
      value3: '',
      tableData: [],
      tableData2: [],
      tableData3: [],
      tableData4: [],
      input: '',
      isDisplay: false,
      state: '',
      AllData: [],
      externalstcd: ''
    }
  },
  methods: {
    nullStcd() {
      this.$message.error('请填写监控站编号!')
    },
    nullDataPackage() {
      this.$message.error('查找不到相关数据!')
    },
    search() {
      this.display = false
      if(this.input == ''){// eslint-disable-line
        this.nullStcd()
        this.isDisplay = false
        return
      }
      this.$axios
        .get('http://localhost:1313/surveyStation/findSurveyStationByStcd/' + this.input) // 根据input提取基本信息
        .then((response) => {
          if(response.data.length == 0){// eslint-disable-line
            this.nullDataPackage()
            this.isDisplay = false
            return
          }
          this.isDisplay = true
          this.tableData = []
          for (var j in response.data) {
            this.tableData.push(response.data[j])
          }
          console.log(this.tableData)
          // 根据input提取降雨量（2）
          // //
          // //
          this.$axios
            .get('http://localhost:1313/precipitation/getPrpByStcd/' + this.input)
            .then((response2) => {
              this.isDisplay = true
              this.tableData2 = []
              for (var j in response2.data) {
                var xxx = { stcd: response2.data[j][0],
                  tm: response2.data[j][1],
                  val: response2.data[j][2] }
                this.tableData2.push(xxx)
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
    },
    querySearch(queryString, cb) {
      var restaurants = this.restaurants
      var results = queryString ? restaurants.filter(this.createFilter(queryString)) : restaurants
      // 调用 callback 返回建议列表的数据
      cb(results)
    },
    createFilter(queryString) {
      return (restaurant) => {
        return (restaurant.stcd.toLowerCase().indexOf(queryString.toLowerCase()) === 0)
      }
    },
    loadAll() {
      return this.AllData
    },
    handleSelect(item) {
      // console.log(item.stcd)
      this.input = item.stcd
    }
  },
  mounted() {
    this.$axios
      .get('http://localhost:1313/surveyStation/findSurveyStation')
      .then((response) => {
        // console.log(response.data)
        for (var j in response.data) {
          this.AllData.push(response.data[j])
        }
        // console.log(this.tableData)
      })
      .catch(function(error) { // 请求失败处理
        console.log(error)
      })
    this.restaurants = this.loadAll()
    // this.input = this.$route.params.ostcd
    // this.search()
  },
  watch: {
    // externalstcd(val) {
    //
    //
    // }
  }
}
</script>

<style scoped>
  .block
  {
    display: flex;
    flex-flow: nowrap;
    justify-content: flex-start;
  }
</style>
