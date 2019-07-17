<template>
  <div class="full" style="padding:10px;">
    <div>
      <el-table
        :data="tableData"
        height="700"
        style="width: 100%">
        <el-table-column
          prop="stnm"
          label="235个监测点"
          width="400">
        </el-table-column>
        <el-table-column
          class="blueFigure"
          fixed="left"
          prop="lgtd"
          label="经度"
          v-if="false">
        </el-table-column>
        <el-table-column
          fixed="left"
          prop="lttd"
          label="纬度"
          v-if="false">
        </el-table-column>
        <el-table-column
          fixed="right"
          label="操作"
          width="100">
          <template slot-scope="scope">
            <el-button @click="displaymaker(scope.row)" type="text" size="small">查看</el-button>
          </template>
        </el-table-column>
      </el-table>
    </div>
    <baidu-map class="map" ak="2KAyaDuGxQlnfX7lA5pD4fYpzzoH21zB" :scroll-wheel-zoom="true"  zoom="11" center="杭州">
      <!--地图视图-->
      <!--显示更多-->
      <!--<div class="more_panel">-->
        <!--<span :class="{'down':isShowPanel}" @click.stop="showPanel">-->
          <!--<span>{{ isShowPanel?'隐藏':'显示' }}</span>搜索列表<i class="el-icon-d-arrow-right" />-->
        <!--</span>-->
      <!--</div>-->
      <!--搜索-->
      <!--<bm-navigation anchor="BMAP_ANCHOR_TOP_RIGHT"></bm-navigation>-->
      <bm-map-type :map-types="['BMAP_NORMAL_MAP', 'BMAP_HYBRID_MAP']" anchor="BMAP_ANCHOR_TOP_LEFT"></bm-map-type>
      <bm-info-window :position="markerPo" :title="infoWindow.title" :show="infoWindow.show" @close="infoWindowClose" @open="infoWindowOpen">
        <p v-text="infoWindow.contents"></p>
        <!--<button @click="clear">Clear</button>-->
      </bm-info-window>
      <!--点标注-->
      <!--<bm-marker :position="markerPo" :title="storeName" :dragging="true" :z-index="999999999" :icon="{url:markerIcon,size: {width: 34, height: 34}}" @click="infoWindowOpen">-->
        <!--<bm-info-window :show="isShowInfo" @close="infoWindowClose" @open="infoWindowOpen">{{ address }}</bm-info-window>-->
      <!--</bm-marker>-->
    </baidu-map>
    <!--<div>{{tableData[0].info}}</div>-->
  </div>
</template>

<script>
// import BaiduMap from 'vue-baidu-map'
import BaiduMap from 'vue-baidu-map/components/map/Map'
// import BmLocalSearch from 'vue-baidu-map/components/search/LocalSearch' // 搜索
// import BmMarker from 'vue-baidu-map/components/overlays/Marker' // 点标注
import BmInfoWindow from 'vue-baidu-map/components/overlays/InfoWindow' // 标注弹窗
export default {
  components: {
    BaiduMap,
    // BmLocalSearch,
    // BmMarker,
    BmInfoWindow
  },
  data() {
    return {
      markerPo: {
        lng: 0, lat: 0
      },
      infoWindow: {
        show: true,
        contents: '',
        title: ''
      },
      isShowPanel: false,
      isShowInfo: false,
      markerIcon: '', // 标注图片
      address: '',
      tableData: []
    }
  },
  methods: {
    infoWindowClose(e) {
      this.infoWindow.show = false
    },
    infoWindowOpen(e) {
      this.infoWindow.show = true
    },
    clear() {
      this.infoWindow.contents = ''
    },
    showPanel() { // 点击标注出现弹框
      this.isShowPanel = !this.isShowPanel
    },
    searchcomplete(arr) {
      this.isShowPanel = true
    },
    displaymaker(row) {
      this.markerPo.lng = row.lgtd
      this.markerPo.lat = row.lttd
      this.infoWindow.show = true
      this.infoWindow.contents = row.stlc
      // console.log(this.markerPo.lng)
    }
  },
  mounted() {
    this.$axios
      .get('http://localhost:1313/surveyStation/findSurveyStation')
      .then((response) => {
        // console.log(response.data)
        this.tableData = []
        for (var j in response.data) {
          this.tableData.push(response.data[j])
        }
      })
      .catch(function(error) { // 请求失败处理
        console.log(error)
      })
  }
}
</script>
<style lang="scss" scoped>
  .full{
    display: flex;
    flex-flow: nowrap;
    justify-content: flex-start;
    width: 100%;
  }
  .map{
    width: 1000px;
    height:700px;
    margin-left: 50px;
    margin-top: 10px;
  }
  .more_panel{
    text-align: center;
    font-size: 12px;
    color: #2878ff;
    padding-top:10px;
    span{
      cursor: pointer;
      i{
        transform:rotate(90deg);
      }
    }
    span.down{
      i{
        transform:rotate(-90deg);
      }
    }
  }
</style>
