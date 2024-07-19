export class Utente {
    private _id: number;
    private _nome: string;
    private _cognome: string;
    private _email: string;
    private _password: string;
  

    constructor(id: number, nome: string, cognome: string, email: string, password: string) {
        this._id = id;
        this._nome = nome;
        this._cognome = cognome;
        this._email = email;
        this._password = password;
    }

    public get id(): number {
        return this._id;
    }

    public set id(id: number) {
        this._id = id;
    }

    public get nome(): string {
        return this._nome;
    }

    public set nome(nome: string) {
        this._nome = nome;
    }

    public get cognome(): string {
        return this._cognome;
    }

    public set cognome(cognome: string) {
        this._cognome = cognome;
    }

    public get email(): string {
        return this._email;
    }

    public set email(email: string) {
        this._email = email;
    }

    public get password(): string {
        return this._password;
    }

    public set password(password: string) {
        this._password = password;
    }
}