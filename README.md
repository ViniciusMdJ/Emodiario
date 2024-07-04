# Emodiario

Bem-vindo ao repositório do **Emodiario**, um aplicativo móvel nativo para Android. Este aplicativo permite que os usuários registrem e acompanhem seus sentimentos em relação a várias atividades diárias, como deslocamento, trabalho, família, entre outros.

## Índice

- [Descrição do Projeto](#descrição-do-projeto)
- [Funcionalidades](#funcionalidades)
- [Instalação](#instalação)
- [Uso](#uso)
- [Tecnologias Utilizadas](#tecnologias-utilizadas)
- [Contribuição](#contribuição)
- [Licença](#licença)
- [Contato](#contato)
- [Documentação](./docs/README.md)

## Descrição do Projeto

O **Emodiario** é uma ferramenta que visa ajudar os usuários a monitorarem suas emoções em relação às atividades do dia a dia. Através do registro contínuo, o aplicativo permite que os usuários obtenham insights sobre seus padrões emocionais e identifiquem áreas que podem necessitar de atenção ou mudança.

## Funcionalidades

- **Categorias de Atividades**: O usuário pode registrar suas atividades diárias e associar sentimentos a elas.
- **Registro de Sentimentos**: Permite aos usuários registrar como estão se sentindo em relação a atividades específicas.
- **Histórico de Registros**: Visualize os registros passados para acompanhar mudanças nos sentimentos ao longo do tempo.

## Instalação

O aplicativo foi feito para se conectar em um servidor remoto, portanto, para executá-lo, é necessário clonar o repositório do servidor e seguir as instruções de instalação. O repositório do servidor pode ser encontrado [aqui](https://github.com/adamisse/Emodiario-API). Para configurar a conexão com o servidor, é necessário alterar a URL base no arquivo [`Constants.kt`](app\src\main\java\com\emodiario\Constraints.kt) para o endereço do servidor.

Para clonar e executar este aplicativo, você precisará do Android Studio instalado em sua máquina. Siga as etapas abaixo:

1. Clone este repositório:
   ```bash
   git clone https://github.com/ViniciusMdJ/Emodiario
   ```

2. Abra o projeto no Android Studio.
3. Conecte um dispositivo Android ou inicie um emulador.
4. Compile e execute o aplicativo.
   
## Uso

1. Abra o aplicativo.
2. Selecione ou crie uma categoria de atividade (e.g., trabalho, deslocamento).
3. Registre seu sentimento atual e adicione uma breve descrição se desejar.
4. Salve o registro.
5. Acesse o histórico para visualizar e analisar seus registros passados.
   
## Tecnologias Utilizadas

- Linguagem: Kotlin
- IDE: Android Studio
- Bibliotecas:
    - Material 3
    - Jetpack Compose
    - Hilt
    - Coil
    - Retrofit

## Licença

Este projeto está licenciado sob a licença MIT. Veja o arquivo [LICENSE](./LICENSE) para mais detalhes.

## Contato

Para qualquer dúvida ou sugestão, sinta-se à vontade para entrar em contato:

- Vinicius Moraes de Jesus
    - Email: vinijaguare@gmail.com
    - LinkedIn: [viniciusmdj](https://www.linkedin.com/in/viniciusmdj/)

- Gabriel de Castro Lima
    - Email: 
    - LinkedIn: 

- Matheus Adami Bernardes
    - Email:
    - LinkedIn:
