<template>
  <el-card class="box-card-component" style="margin-left:8px;">
    <div slot="header" class="box-card-header">
      <img src='https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1562475715116&di=289ab2d821fb6f97e783cdbef56ea70d&imgtype=0&src=http%3A%2F%2F5b0988e595225.cdn.sohucs.com%2Fimages%2F20190703%2Fbc6b83745a514639ba009e13972394cd.jpeg'>
    </div>
    <div style="position:relative;">
      <!--<pan-thumb :image="avatar" class="panThumb" />-->
      <!--<mallki class-name="mallki-text" text="vue-element-admin" />-->
      <p style="color: #409EFF;font-weight: bold">管理单位</p>
      <div style="padding-top:15px;" class="progress-item">
        <span>拱墅区防汛办</span>
        <el-progress :percentage="100" status="success"/>
      </div>
      <div class="progress-item">
        <span>杭州市排水公司</span>
        <el-progress :percentage="100" status="success"/>
      </div>
      <div class="progress-item">
        <span>杭州市林业水利局</span>
        <el-progress :percentage="100" status="success"/>
      </div>
      <div class="progress-item">
        <span>杭州市气象局</span>
        <el-progress :percentage="100" status="success" />
      </div>
      <div class="progress-item">
        <span>河道监管中心</span>
        <el-progress :percentage="100" status="success" />
      </div>
      <div class="progress-item">
        <span>滨江区</span>
        <el-progress :percentage="100" status="success" />
      </div>
    </div>
  </el-card>
</template>

<script>
import { mapGetters } from 'vuex'
// import PanThumb from '@/components/PanThumb'
// import Mallki from '@/components/TextHoverEffect/Mallki'

export default {
  // components: { PanThumb, Mallki },

  filters: {
    statusFilter(status) {
      const statusMap = {
        success: 'success',
        pending: 'danger'
      }
      return statusMap[status]
    }
  },
  data() {
    return {
      statisticsData: {
        article_count: 1024,
        pageviews_count: 1024
      }
    }
  },
  computed: {
    ...mapGetters([
      'name',
      'avatar',
      'roles'
    ])
  }
}
</script>

<style lang="scss" >
.box-card-component{
  .el-card__header {
    padding: 0px!important;
  }
}
</style>
<style lang="scss" scoped>
.box-card-component {
  .box-card-header {
    position: relative;
    height: 220px;
    img {
      width: 100%;
      height: 100%;
      transition: all 0.2s linear;
      &:hover {
        transform: scale(1.1, 1.1);
        filter: contrast(130%);
      }
    }
  }
  .mallki-text {
    position: absolute;
    top: 0px;
    right: 0px;
    font-size: 20px;
    font-weight: bold;
  }
  .panThumb {
    z-index: 100;
    height: 70px!important;
    width: 70px!important;
    position: absolute!important;
    top: -45px;
    left: 0px;
    border: 5px solid #ffffff;
    background-color: #fff;
    margin: auto;
    box-shadow: none!important;
    /deep/ .pan-info {
      box-shadow: none!important;
    }
  }
  .progress-item {
    margin-bottom: 10px;
    font-size: 14px;
  }
  @media only screen and (max-width: 1510px){
    .mallki-text{
      display: none;
    }
  }
}
</style>
