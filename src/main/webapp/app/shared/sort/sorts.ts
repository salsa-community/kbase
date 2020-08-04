export default function buildPaginationQueryOpts(paginationQuery) {
  if (paginationQuery) {
    let sorts = '';
    for (const idx of Object.keys(paginationQuery.sort)) {
      if (sorts.length > 0) {
        sorts += '&';
      }
      sorts += 'sort=' + paginationQuery.sort[idx];
    }
    let filtro = paginationQuery.filtro;
    let contextos = '';
    let eventos = '';
    let fechas = '';

    console.log(filtro);

    if (filtro) {
      if (filtro.contextos && filtro.contextos.length > 0) {
        for (let index = 0; index < filtro.contextos.length; index++) {
          const valor = filtro.contextos[index];
          contextos += '&contexto=' + valor;
        }
      }
      if (filtro.eventos && filtro.eventos.length > 0) {
        for (let index = 0; index < filtro.eventos.length; index++) {
          const valor = filtro.eventos[index];
          eventos += '&evento=' + valor;
        }
      }
      if (filtro.rangoFechas && filtro.rangoFechas.length > 0) {
        console.log(filtro.rangoFechas[0]);
        fechas = '&fechaInicio=' + filtro.rangoFechas[0].toISOString() + '&fechaFin=' + filtro.rangoFechas[1].toISOString();
      }
    }
    return `${sorts}&page=${paginationQuery.page}&size=${paginationQuery.size}${contextos}${eventos}${fechas}`;
  }
  return '';
}
