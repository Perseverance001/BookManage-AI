<template>
  <div class="borrowbook_container">
    <div class="header"><p>查询借阅记录</p></div>
    <div class="banner">
        <div class="card_number">
            <el-input placeholder="请输入借阅证号" v-model="cardNumber">
                <el-button slot="prepend" icon="el-icon-s-order"></el-button>
            </el-input>
        </div>
      <div class="book_number">
        <el-input placeholder="请输入图书编号" v-model="bookNumber">
          <el-button slot="prepend" icon="el-icon-collection"></el-button>
        </el-input>
      </div>
      <div class="query_button">
        <el-button type="warning" @click="goExpire">查询</el-button>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
        cardNumber: "",
      bookNumber: "",
    };
  },
  methods: {
    async goExpire() {
      // 发送axios请求，携带图书编号  如果返回状态码为成功 跳转逾期的页面 否则提示未借出图书
      const { data: res } = await this.$http.get(
        "bookadmin/query_book/" + this.bookNumber + "/" + this.cardNumber
      );
      if (res.status !== 200) {
        return this.$message.error(
          {
            message:res.msg,
            duration:1000
          }
        );
      }
      this.$message.success(res.msg);
      setTimeout(() => {
          this.$router.push({
              name:"bookexpire",
              query:{
                  bookNumber:this.bookNumber,
                  cardNumber:this.cardNumber
              }
          });
      },1000) // 延时1s跳转
    },
  },
};
</script>

<style lang="less" scoped>
.borrowbook_container {
  display: flex;
  flex-direction: column;
  align-items: center;
  .header {
    width: 100%;
    height: 100px;
    // background-color: pink;
    p {
      color: black;
      font-size: 30px;
      text-align: center;
      line-height: 60px;
    }
  }
  .banner {
    display: flex;
    flex-direction: column;
    align-items: center;
    width: 60%;
    height: 400px;
    // background-color: brown;
    div {
      margin-top: 15px;
      // margin-left: ;
    }
    .borrow_date {
      margin-left: 8px;
    }
    .query_button {
      margin-top: 30px;
    }
  }
}
</style>