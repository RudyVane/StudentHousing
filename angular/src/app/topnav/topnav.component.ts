import { Component } from '@angular/core';

import { AccountService } from "../services/account.service";

@Component({
  selector: 'app-topnav',
  templateUrl: './topnav.component.html',
  styleUrls: ['./topnav.component.css']
})
export class TopnavComponent {
  logoUrl: string = 'assets/logoSH.png';

  constructor(
    private accountService: AccountService
  ) { }

  onLogout() {
    this.accountService.logout();
  }
}
