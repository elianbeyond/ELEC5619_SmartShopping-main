<template>
  <div class="home">
    <HomeState/>
  </div>
</template>

<script>
import HomeState from "../components/HomeState/HomeState.vue";
export default {
  name: "Home",
  components: {
    HomeState,
  },

};
</script>

<style>
.home {
  position: relative;
  top:100px;
}
</style>
