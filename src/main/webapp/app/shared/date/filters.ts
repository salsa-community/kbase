import Vue from 'vue';
import { format, formatRelative, parseISO } from 'date-fns';
import { es } from 'date-fns/locale';

export const DATE_FORMAT = 'yyyy-MM-dd';
export const DATE_TIME_FORMAT = 'yyyy-MM-dd HH:mm';

export const DATE_TIME_LONG_FORMAT = "yyyy-MM-dd'T'HH:mm";
export const INSTANT_FORMAT = "yyyy-MM-dd'T'HH:mm:ssZ";
export const ZONED_DATE_TIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ssXXXXX";

export function initFilters() {
  Vue.filter('formatDate', function(value) {
    if (value) {
      return format(parseISO(value), DATE_TIME_FORMAT);
    }
    return '';
  });
  Vue.filter('formatMillis', function(value) {
    if (value) {
      return format(new Date(value), DATE_TIME_FORMAT);
    }
    return '';
  });
  Vue.filter('timeElapsed', function(value) {
    if (value) {
      return formatRelative(new Date(value), new Date(), { locale: es });
    }
    return '';
  });
}
