<template>
    <div class="row justify-content-center">
        <div class="col-8">
            <div v-if="contexto">
                <h2 class="jh-entity-heading"><span v-text="$t('kbaseApp.contexto.detail.title')">Contexto</span> {{contexto.id}}</h2>
                <dl class="row jh-entity-details">
                    <dt>
                        <span v-text="$t('kbaseApp.contexto.nombre')">Nombre</span>
                    </dt>
                    <dd>
                        <span>{{contexto.nombre}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('kbaseApp.contexto.clave')">Clave</span>
                    </dt>
                    <dd>
                        <span>{{contexto.clave}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('kbaseApp.contexto.desc')">Desc</span>
                    </dt>
                    <dd>
                        <span>{{contexto.desc}}</span>
                    </dd>
                </dl>
                <button type="submit"
                        v-on:click.prevent="previousState()"
                        class="btn btn-info">
                    <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
                </button>
                <router-link v-if="contexto.id" :to="{name: 'ContextoEdit', params: {contextoId: contexto.id}}" tag="button" class="btn btn-primary">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.edit')"> Edit</span>
                </router-link>
            </div>
        </div>
    </div>
</template>

<script lang="ts" src="./contexto-details.component.ts">
</script>
