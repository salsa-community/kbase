<template>
    <div class="row justify-content-center">
        <div class="col-8">
            <div v-if="link">
                <h2 class="jh-entity-heading"><span v-text="$t('kbaseApp.link.detail.title')">Link</span> {{link.id}}</h2>
                <dl class="row jh-entity-details">
                    <dt>
                        <span v-text="$t('kbaseApp.link.url')">Url</span>
                    </dt>
                    <dd>
                        <span>{{link.url}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('kbaseApp.link.mensajeError')">Mensaje Error</span>
                    </dt>
                    <dd>
                        <div v-if="link.mensajeErrorId">
                            <router-link :to="{name: 'MensajeErrorView', params: {mensajeErrorId: link.mensajeErrorId}}">{{link.mensajeErrorId}}</router-link>
                        </div>
                    </dd>
                </dl>
                <button type="submit"
                        v-on:click.prevent="previousState()"
                        class="btn btn-info">
                    <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
                </button>
                <router-link v-if="link.id" :to="{name: 'LinkEdit', params: {linkId: link.id}}" tag="button" class="btn btn-primary">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.edit')"> Edit</span>
                </router-link>
            </div>
        </div>
    </div>
</template>

<script lang="ts" src="./link-details.component.ts">
</script>
