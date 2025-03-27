<template>
  <v-app>
    <v-main>
      <v-container>
        <h1 class="text-center text-h4 mb-5 app-title">Package Management</h1>
        
        <!-- Display Available Packages -->
        <packages-view />
        
        <!-- Action Buttons -->
        <v-row justify="center" class="mb-4">
          <v-btn color="primary" dark @click="openDialog">
            Create New Package
          </v-btn>
          <v-btn color="secondary" dark @click="viewMyPackages" class="ml-4">
            View My Packages
          </v-btn>
        </v-row>

        <!-- Add Package Dialog -->
        <add-package
          :currentUser="username"
          ref="addPackageDialog"
        ></add-package>
      </v-container>
    </v-main>
  </v-app>
</template>

<script>
import PackagesView from './components/PackagesView.vue';
import AddPackage from './components/AddPackage.vue';

export default {
  name: 'App',
  components: {
    PackagesView,
    AddPackage,
  },
  data: () => ({
    username: "Courier John",
  }),
  mounted() {
    this.$refs.addPackageDialog.showDialog = false;
  },
  methods: {
    openDialog() {
      this.$refs.addPackageDialog.showDialog = true;
    },
    viewMyPackages() {
      alert("View My Packages functionality will be implemented.");
    },
  },
};
</script>

<style scoped>
.app-title {
  color: #1976d2;
  font-weight: bold;
}

v-container {
  background-color: #f5f5f5;
  padding: 20px;
  border-radius: 10px;
}
</style>
