import { email, maxLength, minLength, required } from 'vuelidate/lib/validators';
import { Component, Inject, Vue } from 'vue-property-decorator';
import UserManagementService from './user-management.service';
import { IUser, User } from '@/shared/model/user.model';
import AlertService from '@/shared/alert/alert.service';

function loginValidator(value) {
  if (typeof value === 'undefined' || value === null || value === '') {
    return true;
  }
  return /^[_.@A-Za-z0-9-]*$/.test(value);
}

const validations: any = {
  userAccount: {
    login: {
      required,
      minLength: minLength(1),
      maxLength: maxLength(254),
      loginValidator
    },
    firstName: {
      maxLength: maxLength(50)
    },
    lastName: {
      maxLength: maxLength(50)
    },
    email: {
      required,
      email,
      minLength: minLength(5),
      maxLength: maxLength(254)
    }
  }
};

@Component({
  validations
})
export default class JhiUserManagementEdit extends Vue {
  @Inject('alertService') private alertService: () => AlertService;
  @Inject('userService') private userManagementService: () => UserManagementService;
  public userAccount: IUser;
  public isSaving = false;
  public authorities: any[] = [];
  public languages: any = this.$store.getters.languages;
  public showErrors = false;
  public errorMessage = '';

  beforeRouteEnter(to, from, next) {
    next(vm => {
      vm.initAuthorities();
      if (to.params.userId) {
        vm.init(to.params.userId);
      }
    });
  }

  public constructor() {
    super();
    this.userAccount = new User();
    this.userAccount.authorities = ['ROLE_USER'];
  }

  public initAuthorities() {
    this.userManagementService()
      .retrieveAuthorities()
      .then(_res => {
        this.authorities = _res.data;
      });
  }

  public init(userId: string): void {
    this.userManagementService()
      .get(userId)
      .then(res => {
        this.userAccount = res.data;
      });
  }

  public previousState(): void {
    (<any>this).$router.go(-1);
  }

  public save(): void {
    this.isSaving = true;
    if (this.userAccount.id) {
      this.userManagementService()
        .update(this.userAccount)
        .then(res => {
          this.returnToList();
          this.alertService().showAlert(this.getMessageFromHeader(res), 'info');
        })
        .catch(error => {
          this.isSaving = false;
        });
    } else {
      this.userAccount.langKey = 'es';
      this.userManagementService()
        .create(this.userAccount)
        .then(res => {
          this.returnToList();
          this.alertService().showAlert(this.getMessageFromHeader(res), 'success');
        })
        .catch(error => {
          this.isSaving = false;
          this.showErrors = true;
          this.errorMessage = this.$t(error.response.data.message).toString();
          console.log(JSON.stringify(error.response, null, 2));
        });
    }
  }

  private returnToList(): void {
    this.isSaving = false;
    (<any>this).$router.go(-1);
  }

  private getMessageFromHeader(res: any): any {
    return this.$t(res.headers['x-kbaseapp-alert'], { param: res.headers['x-kbaseapp-params'] });
  }
}
