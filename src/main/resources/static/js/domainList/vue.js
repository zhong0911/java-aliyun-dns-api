let domains = describeDomains({});
domains = domains['success'] ? domains['info']['Domains']['Domain'] : {};


const App = {
    data() {
        return {
            domains: domains,
        }
    }
}
Vue.createApp(App).mount('#app');

