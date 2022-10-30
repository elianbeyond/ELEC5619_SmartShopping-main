<template>
  <div class="home-state">
    <div class="items" v-for="rows in items" :key="rows.id">
      <home-item
        v-for="item in rows"
        :key="item.id"
        :item="item"
      />
    </div>
  </div>
</template>

<script>
import HomeItem from "./HomeItem.vue";
import axios from "axios";
export default {
  name: "HomeState",
  components: {
    HomeItem,
  },

  data() {
    return {
      items: [],
    };
  },

  created() {
    axios.get("/product/getAll").then((res) => {
      if (res.data.code === 1) {
        let arr =[];
        let row = Math.ceil(res.data.data.length/8);
        for (let i = 0; i < row; i++) {
          arr[i] = [];
          let modLast = res.data.data.length % 8 === 0 ? 8 : res.data.data.length % 8;
          let lastRow = i === (row - 1) ? modLast : 8;
          for (let j = 0; j < lastRow; j++) {
            arr[i][j] = res.data.data[8 * i + j];
          }
        }
        this.items = arr;

      } else {
        console.log(res.data.msg);
      }
    });

  },
};

</script>

<style scoped>
.home-state {
  display: flex;
  flex-flow: column;
  justify-content: center;
  margin-top: 5rem;
  margin-left: 10%;
}

.title {
  font-family: "Montserrat";
  font-size: 4rem;
  color: #5d5d81;
  margin: 0;
  margin-bottom: 2.5rem;
}

.hline {
  width: 80%;
  height: 0.3rem;
  background-color: #bfcde0;
  border-radius: 5rem;
  margin: 2.5rem 0;
}

.sold-out-soon {
  display: flex;
}

.items {
  display: flex;
  gap: 3%;
  margin-bottom: 5%;
}
</style>
