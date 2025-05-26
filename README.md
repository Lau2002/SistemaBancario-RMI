# 🏦 Sistema Bancário com Java RMI

Este projeto simula um sistema bancário básico utilizando Java RMI (Remote Method Invocation), permitindo a comunicação entre um cliente e um servidor de forma remota.


## 📁 Estrutura do Projeto

```
SistemaBancario/
└── src/
    ├── IContaBancaria.java       // Interface remota
    ├── ContaBancaria.java        // Implementação da interface
    ├── ServidorBanco.java        // Código do servidor
    └── ClienteBanco.java         // Código do cliente
```


## 🧰 Pré-requisitos

- Java JDK 21 instalado e configurado
- Terminal de comando (cmd ou PowerShell)


## ⚙️ Compilação e Execução

### 1. Acesse a pasta `src`:


### 2. Compile todos os arquivos `.java`:

```bash
javac *.java
```


### 3. Inicie o `rmiregistry`:

```bash
start rmiregistry
```

> ⚠️ O `rmiregistry` deve ser iniciado **dentro da pasta `src`** onde estão os `.class`.


### 4. Inicie o servidor:

Em outro terminal, no mesmo diretório `src`:

```bash
java ServidorBanco
```


### 5. Inicie o cliente:

Em outro terminal, também na pasta `src`:

```bash
java ClienteBanco
```


