<template>
    <v-container>
      <h2 class="text-h5">Available Packages</h2>
      <v-btn color="primary" outlined class="mb-3" @click="fetchPackages">
        Refresh
      </v-btn>
      <v-data-table
        :items="packages"
        class="elevation-1"
        dense
      >
        <!-- Toolbar Slot -->
        <template v-slot:top>
          <v-toolbar flat>
            <v-toolbar-title>Packages</v-toolbar-title>
            <v-spacer></v-spacer>
          </v-toolbar>
        </template>
  
        <!-- Action Slot for Buttons -->
        <template v-slot:[`item.action`]="{ item }">
          <v-btn color="success" @click="deliverPackage(item.id)">
            Deliver
          </v-btn>
        </template>
      </v-data-table>
    </v-container>
  </template>
  
  <script>
  import axios from 'axios';
  
  export default {
    name: 'PackagesView',
    data() {
      return {
        packages: [],
      };
    },
    methods: {
      async fetchPackages() {
        try {
          const response = await axios.get('http://localhost:8080/packages');
          this.packages = response.data;
        } catch (error) {
          console.error("Error fetching packages:", error);
        }
      },
      deliverPackage(id) {
        alert(`Deliver Package functionality for package ID: ${id} will be implemented.`);
      },
    },
  };
  </script>
  
  <style scoped>
  v-container {
    background-color: #ffffff;
    border: 1px solid #ddd;
    border-radius: 8px;
    padding: 20px;
    margin-bottom: 20px;
  }
  </style>
  