export class User {
  id: number;
  username: string;
  email: string;
  password: string;
  token?: string = '';
  fullName?: string = '';
  registrationDate?: string = '';
  photoURL?: string = '';
  telephone?: string = '';
  age?: number = 0;
  gender?: string = '';
  role?: string = '';
  status?: string = '';
  language?: string = '';
  maxRent?: number = 0;
  prefCity?: string = '';
  prefGender?: string = '';
  prefKitchen?: string = '';
  prefShower?: string = '';
  prefToilet?: string = '';
  prefLiving?: string = '';
  prefInternet?: string = '';
  prefEnergyLabel?: string = '';
  prefPets?: string = '';
  prefSmokingInside?: string = '';
  prefRoommates?: string = '';
  prefDistanceToZipcode?: string = '';
  prefZipcode?: string = '';
  adActive?: boolean = false;
  adMessage?: string = '';

  constructor(id: number, username: string, email: string, password: string);

  constructor(
    id: number,
    username: string,
    email: string,
    password: string,
    fullName?: string,
    registrationDate?: string,
    photoURL?: string,
    telephone?: string,
    age?: number,
    gender?: string,
    role?: string,
    status?: string,
    language?: string,
    maxRent?: number,
    prefCity?: string,
    prefGender?: string,
    prefKitchen?: string,
    prefShower?: string,
    prefToilet?: string,
    prefLiving?: string,
    prefInternet?: string,
    prefEnergyLabel?: string,
    prefPets?: string,
    prefSmokingInside?: string,
    prefRoommates?: string,
    prefDistanceToZipcode?: string,
    prefZipcode?: string,
    adActive?: boolean,
    adMessage?: string
  ) {
    this.id = id;
    this.username = username;
    this.email = email;
    this.password = password;
    this.fullName = fullName;
    this.registrationDate = registrationDate;
    this.photoURL = photoURL;
    this.telephone = telephone;
    this.age = age;
    this.gender = gender;
    this.role = role;
    this.status = status;
    this.language = language;
    this.maxRent = maxRent;
    this.prefCity = prefCity;
    this.prefGender = prefGender;
    this.prefKitchen = prefKitchen;
    this.prefShower = prefShower;
    this.prefToilet = prefToilet;
    this.prefLiving = prefLiving;
    this.prefInternet = prefInternet;
    this.prefEnergyLabel = prefEnergyLabel;
    this.prefPets = prefPets;
    this.prefSmokingInside = prefSmokingInside;
    this.prefRoommates = prefRoommates;
    this.prefDistanceToZipcode = prefDistanceToZipcode;
    this.prefZipcode = prefZipcode;
    this.adActive = adActive;
    this.adMessage = adMessage;
  }
}
