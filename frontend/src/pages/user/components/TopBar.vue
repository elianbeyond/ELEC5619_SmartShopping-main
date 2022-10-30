<template>
  <div class="top-bar">
    <div class="section left">
      <a href="/main#/home">Smart Store</a>
    </div>
    <div class="section centre">User</div>
    <div class="section right">
      <button class="button" v-on:click="signOut">Sign out</button>
    </div>
  </div>
</template>

<script>
import axios from "axios";
export default {
  name: "TopBar",
  methods: {
    signOut() {
      if (confirm("Are you sure you want to sign-out?")) {
        axios.post("/api/session/signout");
        this.$cookies.remove("signedIn");
        // Update state upon signing out
        this.$store.commit("updateSignedIn", null);
        this.$store.commit("signOut");
        this.$store.commit("clearCart");
        window.location.href = "/main#/home";
      }
    },
  },
};
</script>

<style scoped>
a,
a:visited {
  color: inherit;
  text-decoration: none;
}

.top-bar {
  display: flex;
  align-items: center;
  justify-content: space-between;
  width: 100%;
  height: 5rem;
  background-color: #3b3355;
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

.right {
  display: flex;
  justify-content: right;
  padding: 0 2rem 0 0;
}

.button {
  width: 7.5rem;
  height: 2.4rem;
  border: none;
  border-radius: 5rem;
  font-family: "Montserrat";
  font-size: 1rem;
  transition: all ease-in-out 100ms;
  background-color: #5d5d81;
  color: #e5e5f1;
}

.button:hover {
  cursor: pointer;
  background-color: #525271;
}

.button:active {
  background-color: #454566;
}
</style>
