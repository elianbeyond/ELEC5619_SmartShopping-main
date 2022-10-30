<template>
  <div class="modal">
    <p class="title">Please enter your password</p>
    <div class="body">
      <div class="section">
        <div class="with-error">
          <input type="password" v-model="password" class="password" />
          <p class="error" v-if="showError">Password cannot be empty</p>
        </div>
      </div>
      <div class="section buttons">
        <button class="cancel" @click="this.$emit('close')">Cancel</button>
        <button class="confirm" @click="confirm">Confirm</button>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "PasswordModal",
  data() {
    return {
      password: "",
      showError: false,
    };
  },
  methods: {
    confirm() {
      this.showError = false;
      if (this.password === "") {
        this.showError = true;
      } else {
        this.$emit("confirm", this.password);
      }
    },
  },
};
</script>

<style scoped>
p {
  margin: 0;
}
.modal {
  width: 41rem;
  height: 18rem;
  display: flex;
  flex-flow: column;
  align-items: center;
}

.title {
  background-color: #5d5d81;
  width: 100%;
  height: 20%;
  margin: 0;
  border-radius: 1.5rem 1.5rem 0 0;
  display: flex;
  align-items: center;
  justify-content: center;
  font-family: "Montserrat";
  font-size: 1.3rem;
  color: #ffffff;
}

.body {
  width: 100%;
  height: 80%;
  background-color: #ffffff;
  border-radius: 0 0 1.5rem 1.5rem;
  display: flex;
  flex-flow: column;
  align-items: center;
  justify-content: center;
}

.section {
  width: 100%;
  height: 50%;
  display: flex;
  align-items: center;
  justify-content: center;
}

.with-error {
  display: flex;
  flex-flow: column;
  align-items: flex-start;
}

.error {
  font-family: "lato";
  font-size: 12px;
  color: #ff0000;
}

.password {
  width: 20rem;
  height: 2.4rem;
  border-radius: 5rem;
  padding-left: 1rem;
  padding-right: 0.8rem;
  box-sizing: border-box;
  border: 0.2rem solid #bfcde0;
  font-size: 1.1rem;
  /*text-overflow: hidden;*/
  line-height: 2rem;
}

.password:focus {
  outline: none;
}

.buttons {
  height: unset;
  justify-content: right;
  margin-right: 10%;
  gap: 5%;
}

button {
  width: 8rem;
  height: 2.5rem;
  border: none;
  border-radius: 5rem;
  font-family: "Montserrat";
  font-size: 1.2rem;
  transition: all ease-in-out 100ms;
}

.cancel {
  background-color: #bfcde0;
}

.cancel:hover {
  background-color: #99a9c0;
  cursor: pointer;
}

.cancel:active {
  background-color: #7889a1;
}

.confirm {
  background-color: #5d5d81;
  color: #ffffff;
}

.confirm:hover {
  background-color: #525271;
  cursor: pointer;
}
.confirm:active {
  background-color: #454566;
}
</style>
