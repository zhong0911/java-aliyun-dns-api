const App = {
    data() {
        return {
            rawHtml: '<span style="color: red">这里会显示红色！</span>'
        }
    }
}
Vue.createApp(App).mount('#app');

