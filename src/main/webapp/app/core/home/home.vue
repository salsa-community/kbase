<template>
    <div class="row">
        <div class="col-md">
            <div v-if="authenticated">
                <img src="content/images/knowledgebase.gif" alt="ciencia-loader" width=100% />
            </div>
            <div v-if="!authenticated">
                <img src="content/images/login.gif" alt="ciencia-loader" width=100%/>
            </div>
        </div>
    </div>
</template>

<script lang="ts" src="./home.component.ts">
</script>
