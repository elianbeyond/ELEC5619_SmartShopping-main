<template>
  <div class="top-bar">
    <div class="section left">
      <a href="/main#/home">Smart Store</a>
    </div>
    <div class="section centre">Checkout</div>
    <div class="section"></div>
  </div>
</template>

<script>
export default {
  name: "TopBar",
};
</script>

<style scoped>
.top-bar {
  display: flex;
  align-items: center;
  justify-content: space-between;
  width: 100%;
  height: 5rem;
  background-color: #3b3355;
}

.title {
  margin: 0;
  width: calc((100% - 60rem) / 2);
  padding: 0 0 0 2rem;
  font-family: "Montserrat";
  font-size: 2.5rem;
  line-height: 2.5rem;
  color: #e5e5f1;
}

a,
a:visited {
  color: inherit;
  text-decoration: none;
}

.section {
  width: 33%;
}

.left {
  padding: 0 0 0 2rem;
  font-family: "Montserrat";
  font-size: 2.5rem;
  line-height: 2.5rem;
  color: #e5e5f1;
}
.centre {
  display: flex;
  justify-content: center;
  font-family: "Montserrat";
  font-size: 2rem;
  color: #e5e5f1;
}
</style>
