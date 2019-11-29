<template>
    <div class="row justify-content-center">
        <div class="col-8">
            <div v-if="paso">
                <h2 class="jh-entity-heading"><span v-text="$t('kbaseApp.paso.detail.title')">Paso</span> {{paso.id}}</h2>
                <dl class="row jh-entity-details">
                    <dt>
                        <span v-text="$t('kbaseApp.paso.paso')">Paso</span>
                    </dt>
                    <dd>
                        <span>{{paso.paso}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('kbaseApp.paso.desc')">Desc</span>
                    </dt>
                    <dd>
                        <span>{{paso.desc}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('kbaseApp.paso.instruccion')">Instruccion</span>
                    </dt>
                    <dd>
                        <div v-if="paso.instruccionId">
                            <router-link :to="{name: 'InstruccionView', params: {instruccionId: paso.instruccionId}}">{{paso.instruccionId}}</router-link>
                        </div>
                    </dd>
                </dl>
                <button type="submit"
                        v-on:click.prevent="previousState()"
                        class="btn btn-info">
                    <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
                </button>
                <router-link v-if="paso.id" :to="{name: 'PasoEdit', params: {pasoId: paso.id}}" tag="button" class="btn btn-primary">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.edit')"> Edit</span>
                </router-link>
            </div>
        </div>
    </div>
</template>

<script lang="ts" src="./paso-details.component.ts">
</script>
