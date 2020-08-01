<template>
    <div class="row justify-content-center">
        <div class="col-8">
            <div v-if="actividad">
                <h2 class="jh-entity-heading"><span v-text="$t('kbaseApp.actividad.detail.title')">Actividad</span> {{actividad.id}}</h2>
                <dl class="row jh-entity-details">
                    <dt>
                        <span v-text="$t('kbaseApp.actividad.contexto')">Contexto</span>
                    </dt>
                    <dd>
                        <span>{{actividad.contexto}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('kbaseApp.actividad.valor')">Valor</span>
                    </dt>
                    <dd>
                        <span>{{actividad.valor}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('kbaseApp.actividad.desc')">Desc</span>
                    </dt>
                    <dd>
                        <span>{{actividad.desc}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('kbaseApp.actividad.fecha')">Fecha</span>
                    </dt>
                    <dd>
                        <span>{{actividad.fecha | formatDate}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('kbaseApp.actividad.evento')">Evento</span>
                    </dt>
                    <dd>
                        <div v-if="actividad.eventoId">
                            <router-link :to="{name: 'EventoView', params: {eventoId: actividad.eventoId}}">{{actividad.eventoId}}</router-link>
                        </div>
                    </dd>
                </dl>
                <button type="submit"
                        v-on:click.prevent="previousState()"
                        class="btn btn-info">
                    <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
                </button>
                <router-link v-if="actividad.id" :to="{name: 'ActividadEdit', params: {actividadId: actividad.id}}" tag="button" class="btn btn-primary">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.edit')"> Edit</span>
                </router-link>
            </div>
        </div>
    </div>
</template>

<script lang="ts" src="./actividad-details.component.ts">
</script>
