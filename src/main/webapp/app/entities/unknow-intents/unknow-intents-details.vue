<template>
    <div class="row justify-content-center">
        <div class="col-8">
            <div v-if="unknowIntents">
                <h2 class="jh-entity-heading"><span v-text="$t('kbaseApp.unknowIntents.detail.title')">UnknowIntents</span> {{unknowIntents.id}}</h2>
                <dl class="row jh-entity-details">
                    <dt>
                        <span v-text="$t('kbaseApp.unknowIntents.word')">Word</span>
                    </dt>
                    <dd>
                        <span>{{unknowIntents.word}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('kbaseApp.unknowIntents.lastModified')">Last Modified</span>
                    </dt>
                    <dd>
                        <span>{{unknowIntents.lastModified}}</span>
                    </dd>
                </dl>
                <button type="submit"
                        v-on:click.prevent="previousState()"
                        class="btn btn-info">
                    <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
                </button>
                <router-link v-if="unknowIntents.id" :to="{name: 'UnknowIntentsEdit', params: {unknowIntentsId: unknowIntents.id}}" tag="button" class="btn btn-primary">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.edit')"> Edit</span>
                </router-link>
            </div>
        </div>
    </div>
</template>

<script lang="ts" src="./unknow-intents-details.component.ts">
</script>
