<template>
  <div class="login_container">
    <div class="login_title">AI智阅平台</div>
    <div class="login_box">
      <!-- 头像区域 -->
      <div class="avatar_box">
        <img src="../assets/images/dinosaur.jpg" alt="" />
      </div>
      <!-- 注册表单区域 -->
      <el-form ref="registerFormRef" :model="registerForm" :rules="registerFormRules" label-width="0px" class="login_form">
        <!-- 用户名 -->
        <el-form-item prop="username">
          <el-input v-model.trim="registerForm.username" prefix-icon="iconfont icon-yonghu"></el-input>
        </el-form-item>
        <!-- 密码 -->
        <el-form-item prop="password">
          <el-input v-model="registerForm.password" prefix-icon="iconfont icon-mima1" type="password" :show-password="true"></el-input>
        </el-form-item>
        <!-- 邮箱 -->
        <el-form-item prop="email">
          <el-input v-model="registerForm.email" prefix-icon="iconfont icon-youjian" type="email"></el-input>
        </el-form-item>
        <!-- 真实姓名 -->
        <el-form-item prop="cardName">
          <el-input v-model="registerForm.cardName" prefix-icon="iconfont icon-zhenshixingming"></el-input>
        </el-form-item>
        <!-- 按钮区域 -->
        <el-form-item class="btns">
            <el-button type="success" @click="goLogin">登录</el-button> <!-- 添加登录按钮 -->
            <el-button type="info" @click="resetRegisterForm">重置</el-button>
            <el-button type="primary" @click="register" :loading="registerLoading">注册</el-button>
        </el-form-item>
      </el-form>
    </div>
    <!-- 粒子插件特效 -->
    <vue-particles class="login-bg" color="#39AFFD" :particleOpacity="0.7" :particlesNumber="100" shapeType="circle"
      :particleSize="4" linesColor="#8DD1FE" :linesWidth="1" :lineLinked="true" :lineOpacity="0.4" :linesDistance="150"
      :moveSpeed="3" :hoverEffect="true" hoverMode="grab" :clickEffect="true" clickMode="push">
    </vue-particles>
    <div class="footer">
      <span style="font-weight: bold;color:white;margin-bottom: 10px">
        注册页面切换
      </span>
      <span>
        <i class="iconfont icon-guanliyuan" @click="goManage"></i>
      </span>
    </div>
    <div class="footer2">
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      // 注册表单
      registerForm: {
        username: "",
        password: "",
        email: "",
        cardName: ""
      },
      // 注册表单规则的验证对象
      registerFormRules: {
        username: [
          { required: true, message: "用户名不能为空", trigger: "blur" },
          {
            min: 3,
            max: 20,
            message: "长度在 3 到 20 个字符",
            trigger: "blur",
          },
        ],
        password: [
          { required: true, message: "密码不能为空", trigger: "blur" },
          {
            min: 6,
            max: 15,
            message: "长度在 6 到 15 个字符",
            trigger: "blur",
          },
        ],
        email: [
          { required: true, message: "邮箱不能为空", trigger: "blur" },
          { type: "email", message: "请输入正确的邮箱地址", trigger: "blur" }
        ],
        cardName: [
          { required: true, message: "真实姓名不能为空", trigger: "blur" },
          {
            min: 2,
            max: 50,
            message: "长度在 2 到 50 个字符",
            trigger: "blur",
          },
        ]
      },
      registerLoading: false
    };
  },
  methods: {
    resetRegisterForm() {
      this.$refs.registerFormRef.resetFields();
    },
    register() {
      this.$refs.registerFormRef.validate(async (valid) => {
        // 如果表单验证无效，直接返回
        if (!valid) {
          return;
        }
        this.registerLoading = true;
        // 向数据库发送axios请求，如果注册成功，就跳转
        const { data: res } = await this.$http.post(
          "user/register",
          {
            username: this.registerForm.username,
            password: this.registerForm.password,
            email: this.registerForm.email,
            cardName: this.registerForm.cardName
          }
        );
        if (res.status !== 200) {
          this.registerLoading = false;
          return this.$message.error(res.msg);
        }
        this.$message.success("注册成功");
        this.registerLoading = false;
        this.$router.push("/login"); // 跳转到登录页面
      });
    },
    goLogin() { // 添加跳转到登录页面的方法
      this.$router.push("/login");
    },
    goManage() {
      this.$router.push("/loginmanage");
    }
  },
};
</script>

<style lang="less" scoped>
.footer2 {
  position: absolute;
  bottom: 0px;
  left: 35%;
  color: #ccc;

  a {
    color: #ccc;
  }
}

.login_container {
  background: url(https://4kwallpapers.com/images/walls/thumbs_3t/21054.jpg) no-repeat 0px 0px;
  background-size: cover;
  height: 100%;
}

.login_box {
  height: 400px; /* 增加高度以适应新的表单项 */
  width: 450px;
  background-color: #fff;
  border-radius: 3px;
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);

  .avatar_box {
    width: 130px;
    height: 130px;
    border: 1px solid #eee;
    border-radius: 50%;
    padding: 10px;
    box-shadow: 0 0 10px #ddd;
    position: absolute;
    left: 50%;
    transform: translate(-50%, -50%);
    background-color: #fff;

    img {
      width: 100%;
      height: 100%;
      border-radius: 50%;
      background-color: #eee;
    }
  }
}

.login_form {
  position: absolute;
  bottom: 0;
  width: 100%;
  padding: 0 20px;
  box-sizing: border-box;
}

.btns {
  display: flex;
  justify-content: flex-end;
}

.login_title {
  position: relative;
  top: 5%;
  font-size: 36px;
  color: white;
  text-align: center;
  font-weight: 700;
  // 控制字体间距
  letter-spacing: 10px;
}

.footer {
  display: flex;
  position: absolute;
  flex-direction: column;
  bottom: 0;
  right: 0;
  width: 100px;
  height: 120px;

  // background-color: pink;
  span {
    // width: 100%;
    // background-color: red;
    text-align: center;
  }
}
</style>
