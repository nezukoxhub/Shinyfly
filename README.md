# Shinyfly

Shinyfly é um player de música para Android e Android TV que funciona com a sua conta do Spotify.
Você entra com a conta que já usa; o app desenha as próprias telas e toca a música pelo Web Player
oficial do Spotify, que roda dentro do aplicativo.

**Autor e titular dos direitos autorais:** Bruno Henrryke Marcelino de Souza.

## Licença

Shinyfly é software livre: você pode redistribuí-lo e/ou modificá-lo sob os termos da
GNU General Public License versão 3, publicada pela Free Software Foundation. Ele é distribuído
**sem qualquer garantia**. Veja [`LICENSE`](LICENSE).

Os trechos de código de terceiros mantêm a licença e o cabeçalho originais. Veja
[`THIRD_PARTY_NOTICES.md`](THIRD_PARTY_NOTICES.md) e [`NOTICE`](NOTICE).

## Direitos autorais

Copyright (C) 2026 Bruno Henrryke Marcelino de Souza.

O código do Shinyfly é de autoria de Bruno Henrryke Marcelino de Souza, inclusive as partes derivadas dos projetos Myspoti e
Spotui, de que o titular é criador. Cada arquivo de sua autoria traz o aviso no cabeçalho.

Os componentes de terceiros listados em [`THIRD_PARTY_NOTICES.md`](THIRD_PARTY_NOTICES.md) mantêm
os avisos e as licenças de seus autores originais.

## Segurança

Para reportar uma vulnerabilidade, veja [`SECURITY.md`](SECURITY.md).

## Compilação

Requer JDK 21 e Android SDK. Uma compilação de release assinada lê a chave do ambiente
(`MYSPOTI_KEYSTORE`, `MYSPOTI_KEYSTORE_PASSWORD`, `MYSPOTI_KEY_ALIAS`, `MYSPOTI_KEY_PASSWORD`) ou de
`keystore/keystore.properties`, que nunca é versionado. Sem chave, o APK de release sai sem assinatura.

Os nomes das variáveis mantêm o prefixo `MYSPOTI_` porque o código depende deles.
Renomeá-los exige compilar e testar o projeto antes.

```
./gradlew :app:assembleDebug
./gradlew test
```

## Aviso

Shinyfly não é afiliado, não é endossado nem patrocinado pelo Spotify, YouTube, Google ou Apple.
O uso de um serviço de terceiros por um cliente não oficial pode ser restringido pelos termos desse
serviço. Você é responsável pela forma como usa o aplicativo.
