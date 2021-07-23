<template>
  <div>
    <h2><img src="http://img.baidu.com/hi/jx2/j_0003.gif"/>Vue  UEditor + Spring Boot演示</h2>
    <hr/>
    <div  class="preview" v-html="msg"></div>
    <vue-ueditor-wrap v-model="msg" :config="myConfig" ></vue-ueditor-wrap>
  </div>


</template>

<script>
import VueUeditorWrap from 'vue-ueditor-wrap' // ES6 Module
export default {
  name: 'Ueditor',
  components: {
    VueUeditorWrap
  },
  data () {
    return {
      msg: '',
      myConfig: {
        // 编辑器不自动被内容撑高
        autoHeightEnabled: false,
        // 初始容器高度
        initialFrameHeight: 500,
        // 初始容器宽度
        initialFrameWidth: '100%',
        // 上传文件接口（这个地址是我为了方便各位体验文件上传功能搭建的临时接口，请勿在生产环境使用！！！）
        serverUrl: '//localhost:9000/ueditor/server',
        // UEditor 资源文件的存放路径，如果你使用的是 vue-cli 生成的项目，通常不需要设置该选项，vue-ueditor-wrap 会自动处理常见的情况，如果需要特殊配置，参考下方的常见问题2
        UEDITOR_HOME_URL: '/static/ueditor/'
      }
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
h1, h2 {
  font-weight: normal;
}
ul {
  list-style-type: none;
  padding: 0;
}
li {
  display: inline-block;
  margin: 0 10px;
}
a {
  color: #42b983;
}
</style>
