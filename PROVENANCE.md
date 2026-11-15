# Procedência do código-fonte

Este documento diz de onde vem cada parte do código. Ele serve para que a titularidade seja
declarada corretamente. Cada linha tem um estado:

- **Confirmado de terceiros**: o próprio arquivo traz o cabeçalho ou a atribuição do autor original.
  Mantém o aviso e a licença originais.
- **Autoria do titular**: código escrito por Bruno Henrryke Marcelino de Souza, confirmado pelo titular. Traz o aviso
  `Copyright (C) 2026 Bruno Henrryke Marcelino de Souza` no cabeçalho. Inclui o Myspoti (projeto de origem) e o Spotui,
  dos quais o titular declara ser criador.
- **Terceiros**: arquivos com cabeçalho ou atribuição de outro autor. Mantêm o aviso original e
  **não** recebem o nome do titular.

| Caminho | Arquivos `.kt`/`.kts` | Estado | Evidência |
|---|---|---|---|
| `app/src/main/java/com/music/spotui/` (exceto os de terceiros) | 385 | Autoria do titular | Confirmado pelo titular (criador do Spotui). Cabeçalho `Copyright (C) 2026`. |
| Pacote `app.myspoti` em todos os módulos | 195 | Autoria do titular | Confirmado pelo titular (criador do Myspoti). Cabeçalho `Copyright (C) 2026`. Mantidos os cabeçalhos originais "Myspoti … Reuse requires credit". |
| Scripts de build (`*.gradle.kts`) | 10 | Autoria do titular | Cabeçalho `Copyright (C) 2026`. |
| Arquivos de terceiros (BitChord, Convx, Kyant0, Haze, Reorderable, Apache-2.0, MIT, BSD, e `DashFlacRemux.kt` com cabeçalho do Spotui) | 117 | Terceiros | Avisos originais mantidos. Sem o nome do titular. |

## Histórico

- Versões anteriores tinham perguntas abertas sobre Myspoti e Spotui. O titular respondeu: é criador
  dos dois projetos. Os cabeçalhos de copyright foram aplicados a partir dessa confirmação.

## Outros arquivos de c?digo e recursos

| Caminho | Arquivos | Estado | Evid?ncia |
|---|---:|---|---|
| extensions/src/main/resources/app/myspoti/extensions/prelude.js | 1 | Autoria do titular | C?digo do ambiente Myspoti; o titular declarou ser criador do Myspoti. Cabe?alho copyright/SPDX acrescentado nesta revis?o. |

## Recursos bin?rios com proced?ncia ainda n?o comprovada

Os arquivos app/src/main/res/drawable/album.jpeg, app/src/main/assets/silent.mp3, as imagens shynifly_heart.png, shynifly_folder.png, shynifly_download.png, os ?cones/splash/banner e o pacote interno app/src/main/assets/extensions/lossless-flac.zip foram inventariados. N?o foi poss?vel estabelecer a origem/autoria de todos apenas pelos metadados locais. N?o atribuir automaticamente esses recursos ao titular nem remover cr?ditos; confirmar a origem antes de uma redistribui??o p?blica.

O pacote lossless-flac.zip cont?m seu pr?prio LICENSE com o texto da GPL vers?o 3, README.md, manifest.json (autor declarado: Lossless Audio) e main.js. O pacote foi preservado sem altera??es. A identidade do titular dos direitos e a variante exata da concess?o (somente GPL-3.0 ou GPL-3.0-or-later) n?o ficam comprovadas pelo manifesto/arquivo LICENSE isoladamente; confirmar a origem upstream e a autoriza??o de redistribui??o.

## Retifica??o de proced?ncia ? V14

A avalia??o anterior de proced?ncia n?o comprovada para os tr?s recursos abaixo fica superada pela declara??o expressa do titular do Shinyfly, Bruno Henrryke Marcelino de Souza, de que ele pr?prio os criou:
- app/src/main/assets/extensions/lossless-flac.zip, incluindo o pacote de extens?o e seus arquivos internos;
- app/src/main/res/drawable/album.jpeg;
- app/src/main/assets/silent.mp3.

Os arquivos bin?rios foram preservados sem altera??es. O pacote lossless-flac.zip conserva seu LICENSE com o texto da GNU GPL vers?o 3. O manifesto interno continua informando author: Lossless Audio, sem edi??o. A declara??o de autoria n?o remove nem substitui a licen?a GPLv3 ou suas obriga??es de redistribui??o e disponibiliza??o de c?digo-fonte correspondente, quando aplic?veis.

A autoria dos demais recursos gr?ficos/?cones (imagens shynifly_*, launcher, splash e banner de TV) n?o foi confirmada nesta declara??o e continua sem atribui??o presumida.

