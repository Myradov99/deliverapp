<template>
    <v-dialog v-model="showDialog" max-width="500px" class="bg-transparent">
      <div class="dialog-class">
        <h3>Create a New Package</h3>
  
        <!-- Delivery Address -->
        <v-textarea
          label="Delivery Address"
          v-model="currentPackage.deliveryAddress"
          outlined
          dense
        ></v-textarea>
  
        <!-- Package Status -->
        <v-select
          label="Package Status"
          v-model="currentPackage.status"
          :items="statusOptions"
          outlined
          dense
        ></v-select>
  
        <!-- Pay on Delivery -->
        <v-checkbox
          v-model="currentPackage.payOnDelivery"
          label="Pay on Delivery"
        ></v-checkbox>
  
        <!-- Courier ID -->
        <v-text-field
          label="Courier ID (optional)"
          v-model="currentPackage.courierId"
          outlined
          dense
        ></v-text-field>
  
        <!-- Action Buttons -->
        <v-row class="mt-4" justify="space-between">
          <v-btn color="success" @click="createPackage">Create</v-btn>
          <v-btn color="red" @click="closeDialog">Cancel</v-btn>
        </v-row>
      </div>
    </v-dialog>
  </template>
  
  <script>
  import axios from "axios";
  
  export default {
    name: "AddPackage",
    props: {
      currentUser: String,
    },
    data() {
      return {
        showDialog: false,
        currentPackage: {
          deliveryAddress: "",
          status: "NEW",
          payOnDelivery: false,
          courierId: null,
        },
        statusOptions: ["NEW", "PENDING", "DELIVERED"],
      };
    },
    methods: {
      async createPackage() {
        try {
          const response = await axios.post(
            "http://localhost:8080//api/packages/",
            this.currentPackage
          );
          console.log("Package created successfully:", response.data);
          this.closeDialog(); // Close dialog on success
        } catch (error) {
          console.error("Error creating package:", error);
          alert("Failed to create package. Please try again.");
        }
      },
      closeDialog() {
        this.showDialog = false;
        this.currentPackage = {
          deliveryAddress: "",
          status: "NEW",
          payOnDelivery: false,
          courierId: null,
        };
      },
    },
  };
  </script>
  
  <style scoped>
  .dialog-class {
    padding: 20px;
    background: linear-gradient(to right, #2196f3, #21cbf3);
    border-radius: 8px;
    color: white;
    box-shadow: 0 4px 10px rgba(0, 0, 0, 0.2);
  }
  
  h3 {
    margin-bottom: 20px;
    font-weight: bold;
    text-align: center;
    color: white;
  }
  </style>
  