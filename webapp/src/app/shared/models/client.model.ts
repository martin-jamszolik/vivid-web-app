

export class Client {
    id: number;
    name: string;
    address: Address;
    contact: Person;
}

export class Address {
    line1: string;
    line2: string;
    city: string;
    state: string;
    zip: string;

}

export class Person {
    id:number;
    fullName: string;
    phone:number;
    fax: number;
    email: string;
}
