<template>
    <div class="row justify-content-center">
        <div class="col-8">
            <div v-if="instruccion">
                <h2 class="jh-entity-heading"><span v-text="$t('kbaseApp.instruccion.detail.title')">Instruccion</span> {{instruccion.id}}</h2>
                <dl class="row jh-entity-details">
                    <dt>
                        <span v-text="$t('kbaseApp.instruccion.desc')">Desc</span>
                    </dt>
                    <dd>
                        <span>{{instruccion.desc}}</span>
                    </dd>
                </dl>
                <button type="submit"
                        v-on:click.prevent="previousState()"
                        class="btn btn-info">
                    <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
                </button>
                <router-link v-if="instruccion.id" :to="{name: 'InstruccionEdit', params: {instruccionId: instruccion.id}}" tag="button" class="btn btn-primary">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.edit')"> Edit</span>
                </router-link>
            </div>
        </div>
    </div>
</template>

<script lang="ts" src="./instruccion-details.component.ts">
</script>
