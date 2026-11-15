# Política de segurança

## Versões suportadas

Apenas a versão mais recente publicada do Shinyfly recebe correções de segurança.

## Como reportar uma vulnerabilidade

Não abra uma issue pública para um problema de segurança.

Envie o relato em privado para: **shinyflyofficial@gmail.com**

Inclua, se possível:
- a versão afetada e o aparelho (versão do Android, celular ou TV);
- os passos para reproduzir o problema;
- o resultado esperado e o resultado obtido;
- se dados da conta, tokens ou arquivos baixados podem ser expostos.

Prazo de resposta inicial: **7 dias corridos**. Os relatos são tratados em
privado até que uma correção seja publicada. Depois disso, quem reportou é citado, se quiser.

## Escopo

Dentro do escopo: o aplicativo Shinyfly e os módulos deste repositório (autenticação e tratamento de
tokens, armazenamento local e cifragem de downloads, carregamento e verificação de extensões, ponte
entre a WebView e o app, requisições de rede).

Fora do escopo: extensões de terceiros instaladas pelo usuário, o próprio serviço do Spotify e
problemas que exigem um aparelho com acesso root ou comprometido.

## Mecanismos de segurança mantidos neste código

Estes mecanismos não devem ser removidos sem uma revisão de segurança:
- mascaramento de logs (`LogMask`) e remoção de logs verbosos nas versões de release estáveis;
- remoção de query string e fragmento nas URLs registradas em log (`Logbook.noQuery`);
- assinatura de release lida do ambiente ou de um arquivo fora do controle de versão;
- verificação de pacotes de extensões e atestação (`Attest`).
