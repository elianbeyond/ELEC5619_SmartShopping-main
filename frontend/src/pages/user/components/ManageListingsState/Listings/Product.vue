<template>
  <tr class="detail">
    <td></td>
    <td>
      <div class="product-title">{{ details.name }}</div>
    </td>
    <td>{{ details.category }}</td>
    <td>${{ details.price }}</td>
    <td>{{ details.quantity }}</td>
    <td>{{ details.location }}</td>
    <td><button class="remove" @click="remove">Remove</button></td>
    <td></td>
  </tr>
</template>

<script>
export default {
  name: "Product",
  props: {
    details: Object,
  },
  methods: {
    remove() {
      console.log(this.details);
      this.$emit("remove", this.details.id);
    },
  },
};
</script>

<style scoped>
.detail {
  font-family: "Lato";
  font-size: 1.5rem;
  height: 8rem;
}

.product-title {
  display: -webkit-box;
  -webkit-line-clamp: 2;
  -webkit-box-orient: vertical;
  overflow: hidden;
}

.remove {
  width: 7rem;
  height: 2rem;
  border: none;
  border-radius: 5rem;
  font-family: "Montserrat";
  font-size: 1.1rem;
  background-color: #fe4874;
  color: #fefcfd;
  transition: all ease-in-out 100ms;
}

.remove:hover {
  cursor: pointer;
  background-color: #eb3c66;
}

.remove:active {
  background-color: #d92b55;
}
</style>
