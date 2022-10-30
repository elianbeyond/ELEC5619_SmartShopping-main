module.exports = {
  devServer: {
    hot: true,
    port: 8081,
    proxy: {
      "/api": {
        target: "http://localhost:8080",
        changeOrigin: true,
        // rewrite path
        pathRewrite: {
          "^/api": "", // replace '/api' with empty string
        },
      },
    },
  },

  pages: {
    login: {
      entry: "src/pages/login/main.js",
      template: "public/index.html",
      title: "SellPhone - Session",
      chunks: ["chunk-vendors", "chunk-common", "login"],
    },
    main: {
      entry: "src/pages/main/main.js",
      template: "public/index.html",
      title: "SellPhone - Main",
      chunks: ["chunk-vendors", "chunk-common", "main"],
    },
    checkout: {
      entry: "src/pages/checkout/main.js",
      template: "public/index.html",
      title: "SellPhone - Checkout",
      chunks: ["chunk-vendors", "chunk-common", "checkout"],
    },
    user: {
      entry: "src/pages/user/main.js",
      template: "public/index.html",
      title: "SellPhone - User",
      chunks: ["chunk-vendors", "chunk-common", "user"],
    },
  },
};
