<template>
  <b-alert show variant="primary">
    <h5 class="alert-heading">Variables disponibles</h5>
    <b-container class="bv-example-row">
      <b-row>
        <b-col v-for="(element, index) in elements" :key="index">
          <b-badge variant="primary">${{ element }}</b-badge>
        </b-col>
      </b-row>
    </b-container>
  </b-alert>
</template>

<script lang="ts" src="./variables.component.ts">
</script>
