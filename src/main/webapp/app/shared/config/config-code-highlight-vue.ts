import 'vue-code-highlight/themes/duotone-sea.css';
import 'vue-code-highlight/themes/window.css';
import VueCodeHighlight from 'vue-code-highlight/src/CodeHighlight.vue';
import 'prism-es6/components/prism-markup';

export function initCodeHighlightVue(vue) {
  vue.component('code-highlight', VueCodeHighlight);
}
