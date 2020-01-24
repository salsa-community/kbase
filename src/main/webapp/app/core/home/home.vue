<template>
    <div class="row">
        <div class="col-md-2"></div>
        <div class="col-md-8">
            <div v-if="authenticated">
                <img src="content/images/welcome-bot.gif" alt="ciencia-loader"/>
            </div>
            <div v-if="!authenticated">
                <h4 class="display-4" v-text="$t('home.title')"></h4>
                <img src="content/images/knowledgebase.gif" alt="ciencia-loader"/>
            </div>
        </div>
        <div class="col-md-2"></div>
    </div>
</template>

<script lang="ts" src="./home.component.ts">
</script>
