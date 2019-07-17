<template>
  <div class="app-container">
    <div class="block">
      <el-date-picker
        v-model="value2"
        type="date"
        placeholder="选择日期"
        format="yyyy 年 MM 月 dd 日"
        value-format="yyyy-MM-dd"
        :picker-options="pickerOptions"
      />
      <!--<el-input-->
        <!--placeholder="请输入监控点编号"-->
        <!--v-model="stcd"-->
        <!--clearable-->
        <!--style="width: 200px; margin-left: 20px">-->
      <!--</el-input>-->
      <el-autocomplete
        class="inline-input"
        v-model="state"
        :fetch-suggestions="querySearch"
        placeholder="请输入内容"
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
      <el-button type="primary" icon="el-icon-search" style="margin-left: 30px" @click="search" >搜索</el-button>
      <label />
    </div>
    <el-col :xs="{span: 24}" :sm="{span: 12}" :md="{span: 12}" :lg="{span: 6}" :xl="{span: 6}" style="margin-bottom:30px; width: 100%" v-loading="loading" v-if="isDisplay">
      <div id='main' style='width: 100%;height:600px;margin-top: 100px'></div>
    </el-col>
  </div>
</template>

<script>
export default {
  data() {
    return {
      pickerOptions: {
        disabledDate(time) {
          const day = 30 * 24 * 3600 * 1000 * 12 * 5 - 30 * 24 * 3600 * 1000 * 2 - 30 * 24 * 3600 * 650
          return time.getTime() > Date.now() - day || time.getTime() < Date.now() - 8.64e11 + 30 * 24 * 3600 * 1000 * 12 * 8 - 30 * 24 * 3600 * 350
        }
      },
      value1: '',
      value2: '',
      stcd: '',
      ppchartx: [],
      ppval: [],
      pprz: [],
      ppchart: [],
      loading: false,
      isDisplay: false,
      state: '',
      tableData: []
    }
  },
  methods:
      { nullTime() {
        this.$message.error('请选择时间!')
      },
      nullStcd() {
        this.$message.error('请填写监控站编号!')
      },
      nullDataPackage() {
        this.$message.error('查找不到相关数据!')
      },
      // 用日期查找数据库
      search() {
        if (this.value2 == ''){// eslint-disable-line
          this.nullTime()
          return
        }
        if(this.stcd == ''){// eslint-disable-line
          this.nullStcd()
          return
        }
        this.loading = true
        this.isDisplay = true
        this.$axios
          .get('http://localhost:1313/precipitation/findPpValAndTime/' + this.value2 + '/' + this.stcd)
          .then((response) => {
            if(response.data.length == 0){// eslint-disable-line
              this.nullDataPackage()
              this.loading = false
              this.isDisplay = false
              return
            }
            // console.log(response.data)
            this.ppchartx = []
            this.ppval = []
            for (var i in response.data) {
              this.ppchartx.push(response.data[i].tm.substr(0, 10) + ' ' + response.data[i].tm.substr(11, 8))
              this.ppval.push(response.data[i].val)
            }
          })
          .catch(function(error) { // 请求失败处理
            console.log(error)
          })
        this.$axios
          .get('http://localhost:1313/prrsvr/findPrRsvr/' + this.value2 + '/' + this.stcd)
          .then((response) => {
            this.pprz = []
            // console.log(response.data)
            for (var j in response.data) {
              this.pprz.push(response.data[j].rz)
            }
          })
          .catch(function(error) { // 请求失败处理
            console.log(error)
          })
      },
      drawLine() {
        var myChart = this.$echarts.init(document.getElementById('main'))
        var option = {
          title: {
            text: '雨量及水库水位关系图',
            x: 'center',
            align: 'right'
          },
          grid: {
            bottom: 80
          },
          toolbox: {
            feature: {
              dataZoom: {
                yAxisIndex: 'none'
              },
              restore: {},
              saveAsImage: {}
            }
          },
          tooltip: {
            trigger: 'axis',
            axisPointer: {
              type: 'cross',
              animation: false,
              label: {
                backgroundColor: '#505765'
              }
            }
          },
          legend: {
            data: ['水库水位', '降雨量'],
            x: 'left'
          },
          dataZoom: [
            {
              show: true,
              realtime: true,
              start: 65,
              end: 85
            },
            {
              type: 'inside',
              realtime: true,
              start: 65,
              end: 85
            }
          ],
          xAxis: [
            {
              type: 'category',
              boundaryGap: false,
              axisLine: { onZero: false },
              data: this.ppchartx.map(function(str) {
                return str.replace(' ', '\n')
              })
            }
          ],
          yAxis: [
            {
              name: '水库水位(m)',
              type: 'value',
              max: 200
            },
            {
              name: '降雨量(mm)',
              nameLocation: 'start',
              max: 50,
              type: 'value',
              inverse: true
            }
          ],
          series: [
            {
              name: '水库水位',
              type: 'line',
              animation: false,
              areaStyle: {
              },
              lineStyle: {
                width: 1
              },
              markArea: {
                silent: true,
                data: []
              },
              data: this.pprz
            },
            {
              name: '降雨量',
              type: 'line',
              yAxisIndex: 1,
              animation: false,
              areaStyle: {
              },
              lineStyle: {
                width: 1
              },
              markArea: {
                silent: true,
                data: []
              },
              data: this.ppval
            }
          ]
        }
        myChart.setOption(option)
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
        return this.tableData
      },
      handleSelect(item) {
        console.log(item.stcd)
        this.stcd = item.stcd
      }
      },
  mounted() {
    this.$axios
      .get('http://localhost:1313/surveyStation/findSurveyStation')
      .then((response) => {
        // console.log(response.data)
        for (var j in response.data) {
          this.tableData.push(response.data[j])
        }
        // console.log(this.tableData)
      })
      .catch(function(error) { // 请求失败处理
        console.log(error)
      })
    this.restaurants = this.loadAll()
    this.drawLine()
  },
  watch: {
    ppchartx(val) {
      this.drawLine()
      this.loading = false
    }
  }
}
</script>

<style>
  .block
  {
    display: flex;
    flex-flow: nowrap;
    justify-content: flex-start;
  }
</style>
