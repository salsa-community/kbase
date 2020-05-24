<template>
  <div class="info jhi-item-count">
        <span v-if="i18nEnabled"
              v-bind:html="$t('global.item-count')">Mostrando {{first}} - {{second}} de {{total}} elementos.</span>
    <span v-if="!i18nEnabled">
            Mostrando {{((page - 1) * itemsPerPage) === 0 ? 1 : ((page - 1) * itemsPerPage + 1)}} -
            {{(page * itemsPerPage) < total ? (page * itemsPerPage) : total}}
            de {{total}} elementos.
        </span>
  </div>
</template>

<script lang="ts" src="./jhi-item-count.component.ts">
</script>
