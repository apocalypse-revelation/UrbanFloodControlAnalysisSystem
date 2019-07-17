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
    <el-row :gutter="8">
      <el-col :xs="{span: 24}" :sm="{span: 12}" :md="{span: 12}" :lg="{span: 6}" :xl="{span: 6}" style="margin-bottom:30px; width: 100%" v-loading="loading" v-if="isDisplay">
        <div class="load" id='main' style='width: 100%;height:700px;margin-top: 100px;'></div>
      </el-col>
    </el-row>
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
      riverTime: [],
      riverLevel: [],
      riverZ: [],
      loading: false,
      isDisplay: false,
      state: '',
      tableData: []
    }
  },
  methods:
      {
        nullTime() {
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
            .get('http://localhost:1313/river/findRiverByStcdAndTime/' + this.value2 + '/' + this.stcd)
            .then((response) => {
              if(response.data.length == 0){// eslint-disable-line
                this.nullDataPackage()
                this.loading = false
                this.isDisplay = false
                return
              }
              // console.log(response.data)
              this.riverTime = []
              this.riverZ = []
              this.riverLevel = []
              for (var i in response.data) {
                this.riverTime.push(response.data[i].tm.substr(5, 5) + ' ' + response.data[i].tm.substr(11, 8))
                this.riverZ.push(response.data[i].z)
                this.riverLevel.push(response.data[i].wptn)
              }
            })
            .catch(function(error) { // 请求失败处理
              console.log(error)
            })
        },
        drawRiver() {
          var myChart = this.$echarts.init(document.getElementById('main'))
          var timeData = this.riverTime
          // timeData = timeData.map(function(str) {
          //   return str.replace('2009/', '')
          // })
          var option = {
            title: {
              text: '河流水位及水势关系图',
              x: 'center'
            },
            tooltip: {
              trigger: 'axis',
              axisPointer: {
                animation: false
              }
            },
            legend: {
              data: ['水势', '水位'],
              x: 'left'
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
            axisPointer: {
              link: { xAxisIndex: 'all' }
            },
            dataZoom: [
              {
                show: true,
                realtime: true,
                start: 30,
                end: 70,
                xAxisIndex: [0, 1]
              },
              {
                type: 'inside',
                realtime: true,
                start: 30,
                end: 70,
                xAxisIndex: [0, 1]
              }
            ],
            grid: [{
              left: 50,
              right: 50,
              height: '35%'
            }, {
              left: 50,
              right: 50,
              top: '55%',
              height: '35%'
            }],
            xAxis: [
              {
                type: 'category',
                boundaryGap: false,
                axisLine: { onZero: true },
                data: timeData
              },
              {
                gridIndex: 1,
                type: 'category',
                boundaryGap: false,
                axisLine: { onZero: true },
                data: timeData,
                position: 'bottom'
              }
            ],
            yAxis: [
              {
                name: '水势(级)',
                type: 'value',
                max: 5
              },
              {
                gridIndex: 1,
                name: '水位(m)',
                type: 'value'
              }
            ],
            series: [
              {
                name: '水位',
                type: 'line',
                xAxisIndex: 1,
                yAxisIndex: 1,
                symbolSize: 8,
                hoverAnimation: false,
                data: this.riverZ
              },
              {
                name: '水势',
                type: 'line',
                symbolSize: 8,
                hoverAnimation: false,
                data: this.riverLevel
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
    this.drawRiver()
  },
  watch: {
    riverTime(val) {
      this.drawRiver()
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
