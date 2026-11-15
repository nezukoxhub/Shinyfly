# Avisos de componentes de terceiros

O Shinyfly é distribuído sob GNU GPL versão 3 (ver LICENSE). O código original declarado pelo titular é de Bruno Henrryke Marcelino de Souza. Os componentes abaixo mantêm seus próprios titulares, avisos e licenças; este arquivo não transfere a autoria desses componentes ao titular do Shinyfly.

**Escopo deste inventário:** inspeção estática das declarações Gradle presentes nos módulos e dos componentes de terceiros identificados no código/recursos. A lista de bibliotecas diretas abaixo reflete as declarações visíveis nos scripts nesta versão; não é uma SBOM, não enumera todas as dependências transitivas e não confirma o conteúdo final do APK. As dependências transitivas, variantes resolvidas, artefatos nativos e licenças efetivamente empacotadas ainda precisam de relatório Gradle/SBOM em ambiente autorizado.

## Código adaptado e componentes incorporados

### BitChord — GPL-3.0-or-later
https://github.com/kushagrasinghx/BitChord

By the BitChord contributors. The Immersive look is adapted from it. Files carrying an "Adapted from BitChord" header in app/src/main/java/com/music/spotui/ui/immersive/ retain that header and applicable license.

### Spotui — GPL-3.0 (autoria declarada pelo titular)
https://github.com/Spotui/Spotui

O titular declara ser criador do Spotui. A UI nativa do Shinyfly é derivada desse projeto. O arquivo app/src/main/java/com/music/spotui/data/offline/DashFlacRemux.kt mantém o cabeçalho original "Ported from Spotui".

### Convx — GPL-3.0
https://github.com/cosmictaserdev-creator/Convx

app/src/main/java/com/music/spotui/ui/immersive/components/ImmersiveOverscroll.kt é adaptado de IosOverscroll.kt.

### Echo-Music — GPL-3.0
https://github.com/EchoMusicApp/Echo-Music

O efeito Liquid Glass chega ao projeto por meio do LiquidGlass.kt do BitChord e do shell Immersive. Manter os créditos e cabeçalhos de origem correspondentes.

### Apple Music TV — GPL-3.0
https://github.com/JObersi10/apple-music-tv

By JObersi10. A interface Android TV foi adaptada dos arquivos de UI. Conforme a procedência declarada, não foram incorporados playback, DRM, código de rede, ícones, imagens ou marca do projeto.

## Código-fonte e recursos sob licenças permissivas

### compose-floating-tab-bar — Apache-2.0
https://github.com/elyesmansour/compose-floating-tab-bar

Copyright Elyes Mansour. app/.../ui/immersive/shell/FloatingTabBar.kt mantém o cabeçalho Apache-2.0.

### Backdrop — Apache-2.0
https://github.com/Kyant0/backdrop

Copyright 2025 Kyant0. Código vendorizado em app/.../ui/immersive/shell/backdrop/; os arquivos mantêm seus cabeçalhos. Texto da licença: licenses/Apache-2.0.txt.

### liquid_glass_easy — MIT
https://pub.dev/packages/liquid_glass_easy

Copyright (c) 2025 Ahmed Gamil. A animação/pílula em GlassSelectionPill.kt segue LiquidGlassAnimatedNavBar. O aviso MIT integral está em licenses/MIT-liquid-glass-easy.txt.

### Beat This! ? MIT

https://github.com/CPJKU/beat_this

By Francesco Foscarin, Jan Schl?tter and Gerhard Widmer (CPJKU, Johannes Kepler University Linz). O modelo app/src/main/assets/automix/beat-this-small0-int8.onnx ? o checkpoint small0 exportado para ONNX e quantizado para int8. O reposit?rio upstream confirma que o c?digo e os pesos publicados s?o MIT; o aviso integral, incluindo Copyright (c) 2024 Institute of Computational Perception, JKU Linz, Austria, est? em licenses/Beat-This-MIT.txt. N?o est?o inclu?das grava??es de treinamento.

## Pacote de extens?o interno: lossless-flac.zip

O arquivo app/src/main/assets/extensions/lossless-flac.zip ? um pacote interno independente, preservado com seus cinco arquivos (manifest.json, main.js, icon.png, LICENSE e README.md). O README orienta consultar a licen?a inclu?da e o arquivo LICENSE cont?m o texto da GNU GPL vers?o 3. O manifesto informa author: Lossless Audio; o c?digo principal n?o identifica claramente um titular de copyright nem um reposit?rio de origem. Portanto, este invent?rio n?o atribui a autoria do pacote ao titular do Shinyfly e n?o presume que o texto GPL isolado comprove a cadeia de titularidade ou a variante exata da concess?o. Antes de distribuir bin?rios, confirmar a origem, o titular, a licen?a exata (GPL-3.0-only ou GPL-3.0-or-later) e a disponibilidade do c?digo-fonte correspondente.

## Fontes — SIL Open Font License 1.1

### Manrope
https://github.com/google/fonts/tree/main/ofl/manrope

Copyright 2018 The Manrope Project Authors. Os metadados internos de app/src/main/res/font/app_sans.ttf identificam Manrope (Manrope ExtraLight), e os metadados upstream confirmam OFL 1.1. Texto: licenses/Manrope-OFL.txt.

### Inter
https://github.com/rsms/inter

Copyright 2020 The Inter Project Authors. Arquivo app/src/main/res/font/inter.ttf. Texto: licenses/Inter-OFL.txt.

## Bibliotecas declaradas diretamente para produção/runtime

As versões abaixo são as declaradas nos scripts ou no catálogo referenciado. Direta não significa que cada artefato esteja necessariamente dentro de todo APK/variante; isso depende das configurações e resolução Gradle.

### GPL / MPL

- NewPipeExtractor com.github.TeamNewPipe:NewPipeExtractor:v0.26.5 — GPL-3.0-or-later, TeamNewPipe. Dependência direta declarada em :app. A versão efetiva declarada é v0.26.5; o alias newpipe = 0.26.3 do catálogo não é usado por essa declaração direta. Preservar os avisos upstream e disponibilizar o código-fonte correspondente exigido pela licença. https://github.com/TeamNewPipe/NewPipeExtractor/releases/tag/v0.26.5
- Rhino org.mozilla:rhino:1.8.1 — MPL-2.0, Mozilla; dependência direta de :extensions. POM exato confirma MPL-2.0 e descreve o JAR como runtime que exclui XML, ferramentas e wrapper ScriptEngine. licenses/MPL-2.0.txt contém a licença; licenses/Rhino-1.8.1-NOTICE.txt contém o NOTICE.txt da tag 1.8.1, incluindo atribuição V8 (BSD-3-Clause) para partes da conversão de ponto flutuante/testes e atribuição MIT para partes da conversão número-para-string de Raffaello Giulietti. NOTICE-tools.txt do repositório foi deliberadamente não incluído como aviso do JAR runtime porque o artefato declara excluir as ferramentas. Referências: POM https://repo1.maven.org/maven2/org/mozilla/rhino/1.8.1/rhino-1.8.1.pom; tag https://github.com/mozilla/rhino/tree/Rhino1_8_1_Release.

### MIT

- ONNX Runtime Android com.microsoft.onnxruntime:onnxruntime-android:1.23.2 — MIT, Microsoft; declarado diretamente em :app.
- ONNX Runtime JVM com.microsoft.onnxruntime:onnxruntime:1.23.2 — MIT; compileOnly e testImplementation em :automix, para ferramentas/testes JVM; não confundir com a variante Android.

### Apache-2.0

- Reorderable sh.calvin.reorderable:reorderable:3.1.0 — Apache-2.0; declarado em :app. https://github.com/Calvin-LL/Reorderable
- ZXing Core com.google.zxing:core:3.5.3 — Apache-2.0; declarado em :app. https://github.com/zxing/zxing
- Haze dev.chrisbanes.haze:haze:1.3.1 — Apache-2.0; declarado em :app. https://github.com/chrisbanes/haze
- AndroidX, Jetpack Compose, Material 3, Activity Compose, Lifecycle, Navigation, Palette, Media, MediaRouter, Media3 e ProfileInstaller — Apache-2.0 nos componentes AndroidX/Jetpack usados diretamente. As versões e módulos diretos aparecem em app/build.gradle.kts e gradle/libs.versions.toml; o texto comum está em licenses/Apache-2.0.txt.
- Ktor 3.6.1 — Apache-2.0; módulos client-core, client-okhttp, client-content-negotiation, client-encoding e serialization-json declarados em :app.
- OkHttp 5.5.1, okhttp-brotli e okhttp-zstd — Apache-2.0, Square; declarados em :app, :extensions, :api-spotify e :host-android conforme os scripts.
- kotlinx.serialization 1.11.0 e kotlinx.coroutines 1.11.0 — Apache-2.0, JetBrains; declaradas diretamente em vários módulos de produção.
- compose-floating-tab-bar e Backdrop — Apache-2.0; componentes de código-fonte incorporado descritos acima.

### Serviços e SDKs Google com termos próprios

- Google Play services Code Scanner com.google.android.gms:play-services-code-scanner:16.1.0 — dependência direta em :app. É uma API/serviço do Google Play services; não descrevê-la como biblioteca open-source Apache empacotada. Aplicam-se os termos do Google Play services e documentação do SDK: https://developers.google.com/ml-kit/vision/barcode-scanning/code-scanner
- Google Play services Cronet com.google.android.gms:play-services-cronet:18.1.1 — dependência direta em :host-android, via catálogo. A implementação é fornecida pelo Google Play services; observar os termos aplicáveis: https://developers.google.com/android/reference/com/google/android/gms/net/CronetProviderInstaller

### Glide Compose 1.0.0-beta01 — BSD e Apache declarados no POM

- Artefato direto: com.github.bumptech.glide:compose:1.0.0-beta01, declarado em :app.
- O POM Maven publicado para esta versão declara Simplified BSD License e Apache License 2.0. A licença upstream do Glide na revisão v5.0.0-rc01, que aparece como dependência compile do artefato, contém o texto BSD do projeto e avisos separados para third_party/disklrucache (Apache-2.0), third_party/gif_decoder (MIT), gif_encoder e NeuQuant. O arquivo upstream integral foi preservado em licenses/Glide-5.0.0-rc01-LICENSE.txt.
- O POM beta01 também declara estas dependências compile: com.github.bumptech.glide:glide:5.0.0-rc01, com.github.bumptech.glide:ktx:1.0.0-beta01, com.github.bumptech.glide:recyclerview-integration:5.0.0-rc01, androidx.compose.foundation:foundation:1.5.1, androidx.compose.ui:ui:1.5.1, androidx.core:core-ktx:1.12.0 e org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.8.22.
- Limite da verificação: a licença declarada no POM e o LICENSE da revisão upstream foram conferidos; ainda não foi gerada a árvore Gradle resolvida do app. Portanto, não afirmar que todos os componentes citados no LICENSE do repositório Glide estão necessariamente dentro do AAR Compose. As dependências compile e suas transitivas devem ser inventariadas pela árvore Gradle/SBOM antes da publicação.
- Referências: POM exato https://repo1.maven.org/maven2/com/github/bumptech/glide/compose/1.0.0-beta01/compose-1.0.0-beta01.pom; LICENSE upstream https://raw.githubusercontent.com/bumptech/glide/v5.0.0-rc01/LICENSE; documentação https://bumptech.github.io/glide/int/compose.html.

## Referência de protocolo, não dependência incorporada confirmada

### Google Cast cast_channel.proto
https://chromium.googlesource.com/chromium/src/+/main/components/cast_channel/proto/cast_channel.proto

O código do Shinyfly implementa mensagens Cast manualmente. A inspeção do ZIP não encontrou o arquivo cast_channel.proto nem evidência de código Chromium gerado/copied. Portanto, este link é documentado como referência técnica de protocolo, não como componente de terceiros confirmado no pacote. Se uma comparação futura encontrar trechos copiados ou código gerado, revisar a atribuição e a licença BSD-3-Clause do arquivo upstream antes de distribuir.

## Dependências declaradas apenas para testes e ferramentas

Estas declarações são de teste/desenvolvimento e não devem ser apresentadas como bibliotecas runtime do APK de produção:

- JUnit 4 4.13.2 — testes em :app, :automix, :extensions e :host-android; licença Eclipse Public License 1.0.
- JUnit 5 5.13.4, Jupiter e Platform Launcher — testes de :api-spotify e :host-cdp; Eclipse Public License 2.0.
- Robolectric 4.14.1 — testes Android locais; Apache-2.0.
- Roborazzi 1.40.1 e Roborazzi Compose — testes de screenshot; Apache-2.0.
- AndroidX Test Runner 1.6.2, AndroidX Test JUnit 1.2.1 e Compose UI Test — testes; Apache-2.0.
- org.json:json:20240303 — testes; licença JSON.org própria, não rotular como MIT. Preservar o texto upstream caso os testes/artefato sejam redistribuídos: https://github.com/stleary/JSON-java
- net.sf.kxml:kxml2:2.3.0 — parser XML usado em testes. O POM do artefato declara licença BSD-style para as classes org.kxml2 e Public Domain para as classes diretamente em org.xmlpull.v1; avisos incluídos em licenses/kxml2-2.3.0.txt.

## Entradas do catálogo que não foram confirmadas como dependências diretas usadas

A inspeção estática dos scripts não encontrou uso direto dos aliases abaixo: Bouncy Castle (bcprov, bcpkix), Glance, org.brotli:dec e desugaring. Não os declarar como incluídos no APK. O alias NewPipe aponta para 0.26.3, mas a declaração direta real usa 0.26.5. O catálogo foi mantido sem alterações para evitar risco de alteração de build; a limpeza/alinhamento deve ser feita separadamente após verificar build logic e variantes.

## Trademarks

Spotify, YouTube, YouTube Music, Google Cast, Android, Wear OS, Android TV e quaisquer outras marcas citadas pertencem aos respectivos titulares. Shinyfly não é afiliado, endossado ou patrocinado por essas entidades.

## Pendências necessárias antes de chamar o inventário de completo para publicação

1. Gerar runtimeClasspath por variante/módulo e testRuntimeClasspath (ou SBOM/relatório de licenças equivalente) em ambiente autorizado; incluir dependências transitivas, artefatos nativos e versões realmente resolvidas.
2. Glide Compose beta01 POM license and Rhino 1.8.1 runtime NOTICE were checked and archived; recheck if dependency versions change.
3. Preservar o aviso JSON.org de org.json caso os testes sejam distribuídos; a licença JSON.org não deve ser rotulada como MIT.
4. Conferir os termos atuais do Google Play services e o fluxo de avisos no APK.
5. Manter o acesso ao código-fonte correspondente de NewPipeExtractor e ao código-fonte do Shinyfly quando distribuir binários, conforme GPL aplicável.
6. No in-app license screen will be added by project-owner decision; keep notices in source/distribution files and validate requirements for each delivery format.

## Avisos e arquivos de licenca

Por decisao do responsavel pelo projeto, nenhuma tela, botao, menu, dialogo ou outro elemento visivel sera adicionado ao aplicativo para exibir licencas. Os avisos e textos integrais permanecem nos arquivos do codigo-fonte distribuido (LICENSE, NOTICE, este inventario e licenses/). Esta decisao de interface nao elimina obrigacoes de redistribuicao: antes de publicar APKs, verificar os requisitos de cada licenca e fornecer avisos e codigo-fonte correspondente pelos meios de distribuicao adequados. A presenca dos arquivos neste ZIP nao comprova, por si so, que tambem estejam incluidos no APK.

Este inventário melhora os avisos existentes, mas não substitui uma SBOM nem uma revisão jurídica formal.

## Recursos gr?ficos e ?udio sem proced?ncia verific?vel

A inspe??o est?tica identificou app/src/main/res/drawable/album.jpeg, app/src/main/assets/silent.mp3, tr?s imagens shynifly_* e recursos de launcher/splash/TV banner. N?o foram encontrados avisos de licen?a ou metadados suficientes para comprovar a autoria/origem de todos esses bin?rios. N?o foram adicionados cr?ditos ou licen?as presumidos. Confirmar a proced?ncia dos arquivos antes de publica??o externa; os ?cones repetidos em densidades diferentes devem ser tratados como variantes do mesmo recurso, n?o como obras independentes.

## Retifica??o de autoria de recursos ? V14

A classifica??o anterior destes tr?s recursos como de proced?ncia n?o comprovada fica superada pela declara??o expressa do titular, Bruno Henrryke Marcelino de Souza, de que ele pr?prio os criou:
- app/src/main/assets/extensions/lossless-flac.zip, incluindo o pacote de extens?o e seus arquivos internos;
- app/src/main/res/drawable/album.jpeg;
- app/src/main/assets/silent.mp3.

O pacote lossless-flac.zip foi preservado sem altera??es. Seu arquivo LICENSE interno cont?m o texto da GNU GPL vers?o 3 e continua aplic?vel conforme seus termos. O manifesto interno permanece com author: Lossless Audio, pois n?o foi modificado. A declara??o de autoria n?o remove a licen?a GPLv3, n?o altera avisos existentes e n?o dispensa a disponibiliza??o do c?digo-fonte correspondente nem outras obriga??es aplic?veis ? redistribui??o.

A autoria dos demais recursos gr?ficos/?cones (imagens shynifly_*, launcher, splash e banner de TV) n?o foi confirmada nesta declara??o.

