<template>
  <!--基础存放容器-->
  <div class="swiper-container">
    <!-- 需要进行轮播的部分 -->
    <div class="swiper-wrapper">
      <!-- 每个节点 -->
      <div class="swiper-slide">
        <img
          data-src="https://img0.baidu.com/it/u=2958632508,1917305803&fm=253&fmt=auto&app=120&f=JPEG?w=750&h=500"
          alt="swiper1"
          class="swiper-lazy"
        />
      </div>
      <div class="swiper-slide">
        <img
          data-src="https://4kwallpapers.com/images/walls/thumbs_3t/911.jpg"
          alt="swiper2"
          class="swiper-lazy"
        />
        <div class="swiper-lazy-preloader"></div>
      </div>
      <div class="swiper-slide">
        <img
          data-src="https://4kwallpapers.com/images/walls/thumbs_3t/12870.jpg"
          alt="swiper3"
          class="swiper-lazy"
        />
        <div class="swiper-lazy-preloader"></div>
      </div>
    </div>

    <!--如果需要使用分页器-->
    <div class="swiper-pagination swiper-pagination-white"></div>

    <!-- 如果需要使用前进后退按钮 -->
    <div class="swiper-button-prev swiper-button-white"></div>
    <div class="swiper-button-next swiper-button-white"></div>
  </div>
</template>

<script>
import swiper from "swiper";

export default {
  name: "",
  data() {
    return {};
  },
  mounted() {
    this.initSwiper();
  },
  methods: {
    initSwiper() {
      new Swiper(".swiper-container", {
        //设置轮播的循环方式
        loop: true,

        //设置自动播放间隔时间
        autoplay: 2000,

        // 轮播效果,默认为平滑轮播
        effect: "slide",

        //分页器
        pagination: ".swiper-pagination",

        //前进后退按钮
        prevButton: ".swiper-button-prev",
        nextButton: ".swiper-button-next",

        // 用户中断轮播后续播
        autoplayDisableOnInteraction: false,
        //懒加载
        lazyLoading: true,
      });
    },
  },
};
</script>

<style scoped>
@import "../../../node_modules/swiper/dist/css/swiper.css";

.swiper-container {
    width: 100%;
    height: 100%;
}

.swiper-slide img {
  width: 100%;
  height: 100%;
  object-fit: cover;
}
</style>