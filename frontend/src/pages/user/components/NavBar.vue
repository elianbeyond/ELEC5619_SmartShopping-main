<template>
  <div class="nav-bar">
    <div class="fullname">{{ this.$store.state.fullname }}</div>
    <button
      class="nav-button"
      v-on:click="goTo('/profile')"
      :disabled="disabled === '/profile'"
    >
      Edit Profile
    </button>
    
    <button
      class="nav-button"
      v-on:click="goTo('/listings')"
      :disabled="disabled === '/listings'"
    >
      Manage Listings
    </button>
    <button
      class="nav-button"
      v-on:click="goTo('/newproduct')"
      :disabled="disabled === '/newproduct'"
    >
      Add New Product
    </button>
  </div>
</template>

<script>
import axios from "axios";
export default {
  name: "NavBar",
  data() {
    return {
      fullname: "Unknown",
      disabled: "",
    };
  },
  mounted() {
            this.getData()
        },
  methods: {
    goTo(target) {
      window.location.href = "/user#" + target;
      this.disabled = target;
    },
    getData() {
    this.disabled = window.location.hash.replace("#", "");
    axios.get("/api/user/name").then((response) => {
      
        this.fullname =response.data;
        console.log(this.fullname)
        this.$store.commit('updateName', this.fullname)
    });
  },
  },
  
};
</script>

<style scoped>
.nav-bar {
  width: 22rem;
  height: 100%;
  background-color: #5d5d81;
}

.fullname {
  display: flex;
  width: 100%;
  height: 10rem;
  align-items: center;
  justify-content: center;
  font-family: "Montserrat";
  font-size: 2rem;
  color: #e5e5f1;
}

.nav-button {
  text-decoration: none;
  font-family: "Montserrat";
  font-size: 1.5rem;
  color: #e5e5f1;
  width: 100%;
  height: 5rem;
  display: flex;
  align-items: center;
  justify-content: center;
  border: none;
  background: transparent;
  transition: all ease-in-out 100ms;
}

.nav-button:hover {
  cursor: pointer;
  background-color: #70709a;
}

.nav-button:disabled {
  cursor: default;
  background-color: #ffffff;
  color: #5d5d81;
}
</style>
