<template>
  <el-container class="home-container">
    <!-- 头部区域 -->
    <el-header>
      <div>
        <!-- <img src="../assets/heima.png" alt="" /> -->
        <span>AI智阅</span>
      </div>
      <div>
        <div>
          <el-avatar src="https://4kwallpapers.com/images/walls/thumbs_2t/16148.jpg" :size="40"
            style="margin-right: 10px"></el-avatar>
        </div>

        <div class="user">用户:{{ this.user.cardName }}</div>
        <el-button type="info" @click="logout">退出</el-button>
      </div>
    </el-header>
    <!-- 页面主体区域 -->
    <el-container>
      <!-- 侧边栏 -->
      <el-aside width="200px">
        <!-- 侧边栏菜单区域 -->
        <el-menu :default-active="activePath" class="el-menu-vertical-demo" background-color="#fff" text-color="black"
          active-text-color="#ffd04b" :router="true" :collapse-transition="false">
          <el-menu-item index="index" @click="saveNavState('index')">
            <i class="iconfont icon-shouye"></i>
            <span slo="title">首页</span>
          </el-menu-item>
          <el-menu-item index="search" @click="saveNavState('search')">
            <i class="iconfont icon-sousuowenjian"></i>
            <span slot="title">图书查询</span>
          </el-menu-item>
          <el-menu-item index="rule" @click="saveNavState('rule')">
            <i class="iconfont icon-guize"></i>
            <span slot="title">读者规则</span>
          </el-menu-item>
          <el-menu-item index="notice" @click="saveNavState('notice')">
            <i class="iconfont icon-gonggao"></i>
            <span slot="title">查看公告</span>
          </el-menu-item>
          <el-menu-item index="information" @click="saveNavState('information')">
            <i class="iconfont icon-gerenxinxi"></i>
            <span slot="title">个人信息</span>
          </el-menu-item>
          <el-menu-item index="borrow" @click="saveNavState('borrow')">
            <i class="iconfont icon-tushujieyuexinxi"></i>
            <span slot="title">借阅信息</span>
          </el-menu-item>
          <el-menu-item index="violation" @click="saveNavState('violation')">
            <i class="iconfont icon-weifaweizhangxinxi"></i>
            <span slot="title">违章信息</span>
          </el-menu-item>
          <el-menu-item index="comment" @click="saveNavState('comment')">
            <i class="iconfont icon-duzheliuyan"></i>
            <span slot="title">读者留言</span>
          </el-menu-item>
          <el-menu-item index="intelligent" @click="saveNavState('intelligent')">
            <i class="iconfont icon-tuijianfantuijian"></i>
            <span slot="title">智能推荐</span>
          </el-menu-item>
<!--          <el-menu-item index="chat" @click="saveNavState('chat')">-->
<!--            <i class="el-icon-monitor"></i>-->
<!--            <span slot="title">图书反馈</span>-->
<!--          </el-menu-item>-->
        </el-menu>
      </el-aside>
      <!-- 右侧内容主体 -->
      <el-main>
        <!-- 路由占位符 -->
        <router-view></router-view>
        <div class="footer">
<!--          <el-popover placement="top-start" :width="150" trigger="hover">-->
<!--            <p slot="reference"> 联系作者|   ©2022-2023 By Per<br /> </p>-->
<!--            <img-->
<!--                src="https://pic.yupi.icu/5563/202312061315664.png"-->
<!--                style="height: 100px; width: 100px"-->
<!--            />-->
<!--          </el-popover>-->
<!--          <a href="https://beian.miit.gov.cn">备案号:浙ICP备2023044565号-2 |</a>-->
<!--          <a href="https://beian.mps.gov.cn/#/query/webSearch">-->
<!--            <img-->
<!--                src="https://xxx.xiaobaitiao.icu/img/icu/202312211243636.png"-->
<!--                style="height: 16px; width: 16px; margin: 5px 0px 0px 5px"-->
<!--            />-->
<!--            浙公网安备33028202001002号-->
<!--          </a>-->
        </div>
      </el-main>
    </el-container>
  </el-container>
</template>

<script>
export default {
  data() {
    return {
      //左侧菜单数据
      menulist: [],
      iconsObj: {
        125: "iconfont icon-user",
        103: "iconfont icon-tijikongjian",
        101: "iconfont icon-shangpin",
        102: "iconfont icon-danju",
        145: "iconfont icon-baobiao",
      },
      //被激活的链接地址
      activePath: "",
      user: {
        userId: Number,
        cardNumber: Number,
        ruleNumber: Number,
        status: Number,
        cardName: "",
        username: "",
        password: "",
        createTime: "",
        updateTime: "",
      },
    };
  },
    async created() {
        // this.getMenuList();
        this.activePath = window.sessionStorage.getItem("activePath");
        // console.log(this.activePath)
        // 先获取sessionStorage中的id
        const stringId = window.sessionStorage.getItem("userId");
        const id = parseInt(stringId);
        this.user.userId = id;
        const { data: res } = await this.$http.post("user/getData", this.user);
        console.log(res);
        window.sessionStorage.setItem('cardNumber', res.data.cardNumber)
        this.user = res.data;
    },
    async mounted() { },
  methods: {
    logout() {
      window.sessionStorage.clear();
      this.$router.push("/login");
    },

    saveNavState(activePath) {
      window.sessionStorage.setItem("activePath", activePath);
      this.activePath = activePath;
    },
    toGitee() {
      console.log(1123);
    }
  },
};
</script>

<style lang="less" scoped>
.footer {
  position: fixed;
  bottom: 0px;
  left: 40%;
  color: #ccc;

  a {
    color: #ccc;
  }
}

.home-container {
  height: 100%;
}

.el-header {
  background-color: rgb(34, 34, 34);
  display: flex;
  justify-content: space-between;
  padding-left: 0px;
  align-items: center;
  color: #fff;
  font-size: 20px;
  border-radius: 10px;

  >div {
    display: flex;
    align-items: center;

    span {
      margin-left: 15px;
    }
  }
}

.el-aside {
  background-color: #fff;

  .el-menu {
    border-right: none;
  }
}

.el-main {
  background-color: #eaedf1;
}

.iconfont {
  margin-right: 10px;
}

.toggle-button {
  background-color: #4a5064;
  font-size: 10px;
  line-height: 24px;
  color: #fff;
  text-align: center;
  //设置文字之间的间距
  letter-spacing: 0.2em;
  cursor: pointer;
}

.user {
  margin-right: 15px;
  color: #ccc;
  font-size: 16px;
}

.el-menu-item:hover {
  background-color: rgb(51, 122, 183) !important;
}

// .el-menu-item{
//   color:rgb(135, 206, 235) !important;
// }</style>
