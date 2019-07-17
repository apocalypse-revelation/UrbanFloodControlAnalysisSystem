<template>
  <div class="dashboard-container">
    <h1 style="text-align: center;padding-right: 100px">城市防汛防涝分析系统</h1>
    <el-row :gutter="8">
      <el-col :xs="{span: 24}" :sm="{span: 12}" :md="{span: 12}" :lg="{span: 6}" :xl="{span: 6}" style="margin-bottom:30px;">
        <box-card />
      </el-col>
      <el-col :xs="{span: 24}" :sm="{span: 12}" :md="{span: 12}" :lg="{span: 6}" :xl="{span: 6}" style="margin-bottom:30px;margin-left: 130px">
          <el-table
            :data="tableData"
            stripe
            style="border-radius: 10px;box-shadow: 0 2px 4px rgba(0, 0, 0, .12), 0 0 6px rgba(0, 0, 0, .04)">
            <el-table-column
              prop="date"
              label="监测起始时间"
              width="180">
            </el-table-column>
            <el-table-column
              prop="name"
              label="2000-01-01 00:00:00"
              width="180">
            </el-table-column>
          </el-table>
      </el-col>
      <el-col :xs="24" :sm="24" :lg="8" style="padding-top: 10px; margin-left: 100px">
          <div id="stcdpiechart" style='width: 100%;height:550px'></div>
      </el-col>
    </el-row>
    <div id="stcdbar" style='width: 100%;height:350px'></div>
    <!--<div id="stnmCloud" style='width: 100%;height:1050px'></div>-->
      <div align="center">监控河流</div>
  </div>
</template>

<script>
import { mapGetters } from 'vuex'
import BoxCard from '../dashref/admin/components/BoxCard'
import 'echarts'

export default {
  name: 'Dashboard',
  computed: {
    ...mapGetters([
      'name'
    ])
  },
  components: {
    BoxCard
    // PieChart
  },
  data() {
    return {
      op1: {},
      op2: {},
      tableData: [{
        date: '监测结束时间',
        name: '2014-10-23 09:50:19'
      }, {
        date: '',
        name: ''
      }, {
        date: '主要流域',
        name: '钱塘江⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀长江太湖区⠀⠀⠀⠀⠀⠀⠀⠀⠀其他流域'
      }, {
        date: '主要水库',
        name: '四岭水库⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀康门水库⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀黄石垅水库⠀⠀⠀⠀⠀⠀⠀⠀⠀仙伯坑水库⠀⠀⠀⠀⠀⠀⠀⠀⠀骑坑水库⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀石门水库⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀馒头山水库'
      }, {
        date: '涉及区域',
        name: '上城区⠀⠀⠀⠀下城区⠀⠀⠀西湖区⠀⠀⠀⠀滨江区⠀⠀⠀江干区⠀⠀⠀⠀拱墅区⠀⠀⠀萧山区⠀⠀⠀⠀余杭区⠀⠀⠀临安区'
      }]
    }
  },
  methods: {
    drawStcdPieChart() {
      var stcdPieChart = this.$echarts.init(document.getElementById('stcdpiechart'))
      var op1 = {
        title: {
          text: '监控点数量',
          x: 'center'
        },
        tooltip: {
          trigger: 'item',
          formatter: '{a} <br/>{b} : {c} ({d}%)'
        },
        legend: {
          orient: 'horizontal',
          data: ['拱墅区防汛办', '杭州市排水公司', '杭州市林业水利局', '杭州市气象局', '河道监管中心', '滨江区'],
          left: 125,
          top: 50
        },
        series: [
          {
            name: '监控点数',
            type: 'pie',
            radius: '55%',
            center: ['50%', '60%'],
            data: [
              { value: 1, name: '拱墅区防汛办' },
              { value: 60, name: '杭州市排水公司' },
              { value: 60, name: '杭州市林业水利局' },
              { value: 104, name: '杭州市气象局' },
              { value: 2, name: '河道监管中心' },
              { value: 8, name: '滨江区' }
            ],
            itemStyle: {
              emphasis: {
                shadowBlur: 10,
                shadowOffsetX: 0,
                shadowColor: 'rgba(0, 0, 0, 0.5)'
              }
            }
          }
        ]
      }
      stcdPieChart.setOption(op1)
    },
    drawStNameCloud() {
      function createRandomItemStyle() {
        return {
          normal: {
            color: 'rgb(' + [
              Math.round(Math.random() * 160),
              Math.round(Math.random() * 160),
              Math.round(Math.random() * 160)
            ].join(',') + ')'
          }
        }
      }
      var stnameCloud = this.$echarts.init(document.getElementById('stnmCloud'))
      var op3 = {
        title: {
          text: 'Google Trends',
          link: 'http://www.google.com/trends/hottrends'
        },
        tooltip: {
          show: true
        },
        series: [{
          name: 'Google Trends',
          type: 'wordCloud',
          size: ['80%', '80%'],
          textRotation: [0, 45, 90, -45],
          textPadding: 0,
          autoSize: {
            enable: true,
            minSize: 14
          },
          data: [
            {
              name: 'Macys',
              value: 6181,
              itemStyle: createRandomItemStyle()
            },
            {
              name: 'Amy Schumer',
              value: 4386,
              itemStyle: createRandomItemStyle()
            },
            {
              name: 'Jurassic World',
              value: 4055,
              itemStyle: createRandomItemStyle()
            },
            {
              name: 'Charter Communications',
              value: 2467,
              itemStyle: createRandomItemStyle()
            },
            {
              name: 'Chick Fil A',
              value: 2244,
              itemStyle: createRandomItemStyle()
            },
            {
              name: 'Planet Fitness',
              value: 1898,
              itemStyle: createRandomItemStyle()
            },
            {
              name: 'Pitch Perfect',
              value: 1484,
              itemStyle: createRandomItemStyle()
            },
            {
              name: 'Express',
              value: 1112,
              itemStyle: createRandomItemStyle()
            },
            {
              name: 'Home',
              value: 965,
              itemStyle: createRandomItemStyle()
            },
            {
              name: 'Johnny Depp',
              value: 847,
              itemStyle: createRandomItemStyle()
            },
            {
              name: 'Lena Dunham',
              value: 582,
              itemStyle: createRandomItemStyle()
            },
            {
              name: 'Lewis Hamilton',
              value: 555,
              itemStyle: createRandomItemStyle()
            },
            {
              name: 'KXAN',
              value: 550,
              itemStyle: createRandomItemStyle()
            },
            {
              name: 'Mary Ellen Mark',
              value: 462,
              itemStyle: createRandomItemStyle()
            },
            {
              name: 'Farrah Abraham',
              value: 366,
              itemStyle: createRandomItemStyle()
            },
            {
              name: 'Rita Ora',
              value: 360,
              itemStyle: createRandomItemStyle()
            },
            {
              name: 'Serena Williams',
              value: 282,
              itemStyle: createRandomItemStyle()
            },
            {
              name: 'NCAA baseball tournament',
              value: 273,
              itemStyle: createRandomItemStyle()
            },
            {
              name: 'Point Break',
              value: 265,
              itemStyle: createRandomItemStyle()
            }
          ]
        }]
      }
      stnameCloud.setOption(op3)
    },
    drawStcdBar() {
      var stcdBar = this.$echarts.init(document.getElementById('stcdbar'))
      var op2 = {
        xAxis: {
          type: 'category',
          data: ['上塘河', '下塘河', '下沙', '东新河', '东河', '中河', '二号坝河', '五常港', '余杭塘河', '六塘汶漾', '冯家港河', '北苕溪', '南应加河', '南苕溪', '古新河', '古荡湾', '官河', '幸福河', '新塘河', '新开河', '沿山河', '浦阳江', '紫金港', '莲花港', '西湖', '西溪', '贴沙河', '运河', '钱塘江']
        },
        yAxis: {
          type: 'value'
        },
        series: [{
          data: [4, 1, 1, 1, 3, 4, 1, 1, 3, 1, 1, 5, 1, 1, 2, 1, 1, 1, 3, 2, 3, 1, 1, 1, 1, 5, 2, 4, 8],
          type: 'bar'
        }]
      }
      stcdBar.setOption(op2)
    }
  },
  mounted() {
    this.drawStcdPieChart()
    this.drawStcdBar()
    this.drawStNameCloud()
  }
}
</script>

<style lang="scss" scoped>
.dashboard {
  &-container {
    margin: 30px;
  }
  &-text {
    font-size: 30px;
    line-height: 46px;
  }
}
</style>
