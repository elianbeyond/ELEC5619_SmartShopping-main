<template>
  <div class="home-item">
    <a :href="'/main#/item/' + item.id">
      <img :src="item.imglocation" alt="item image" />
      <p>{{ item.name }}</p>
      <p>Stock: {{ item.quantity }}</p>
      <p>${{ item.price }}</p>
    </a>
  </div>
</template>

<script>
export default {
  name: "HomeItem",
  props: {
    item: Object,
  },

};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
img {
  width: 12.5rem;
}

a {
  text-decoration: none;
}

p {
  font-family: "Montserrat";
  font-size: 2rem;
  color: #5d5d81;
  padding: 0;
  margin: 0;
  margin-top: 1rem;
}

.home-item {
  width: 13rem;
  height: 16rem;
  text-align: center;
}

.home-item:hover {
  cursor: pointer;
}
</style>
