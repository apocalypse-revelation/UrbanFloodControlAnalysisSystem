<template>
  <div class="app-container">
    <div id="avgriver" style='width: 100%;height:400px'></div>
    <div id="avgrsvr" style='width: 100%;height:400px;margin-top: 20px'></div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      time: [],
      avg: [],
      time2: [],
      avg2: []
    }
  },
  methods: {
    drawAvgRiver() {
      var myChart = this.$echarts.init(document.getElementById('avgriver'))
      var option = {
        title: {
          text: '历月平均水位统计图',
          subtext: '2000/12 <---> 2014/10',
          x: 'center',
          align: 'right'
        },
        xAxis: {
          type: 'category',
          data: this.time
        },
        yAxis: {
          type: 'value'
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
        series: [{
          data: this.avg,
          type: 'line',
          smooth: true
        }]
      }
      myChart.setOption(option)
    },
    drawAvgRsvr() {
      var myChart = this.$echarts.init(document.getElementById('avgrsvr'))
      var option = {
        title: {
          text: '历月平均水库水位统计图',
          subtext: '2009/07 <---> 2014/10',
          x: 'center',
          align: 'right'
        },
        xAxis: {
          type: 'category',
          boundaryGap: false,
          data: this.time2
        },
        yAxis: {
          type: 'value'
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
        series: [{
          data: this.avg2,
          type: 'line',
          areaStyle: {}
        }]
      }
      myChart.setOption(option)
    }
  },
  mounted() {
    this.drawAvgRiver()
    this.drawAvgRsvr()
    this.$axios
      .get('http://localhost:1313/avgRiver/findAvgRiver')
      .then((response) => {
        // console.log(response.data)
        for (var j in response.data) {
          this.time.push(response.data[j].tm)
          this.avg.push(response.data[j].avgz)
        }
        console.log(this.time)
        console.log(this.avg)
      })
      .catch(function(error) { // 请求失败处理
        console.log(error)
      })
    this.$axios
      .get('http://localhost:1313/avgRsvr/findAvgRsvr')
      .then((response) => {
        // console.log(response.data)
        for (var j in response.data) {
          this.time2.push(response.data[j].tm)
          this.avg2.push(response.data[j].avgrz)
        }
        console.log(this.time2)
        console.log(this.avg2)
      })
      .catch(function(error) { // 请求失败处理
        console.log(error)
      })
  },
  watch: {
    time(val) {
      this.drawAvgRiver()
    },
    time2(val) {
      this.drawAvgRsvr()
    }
  }
}
</script>

<style scoped>
</style>

