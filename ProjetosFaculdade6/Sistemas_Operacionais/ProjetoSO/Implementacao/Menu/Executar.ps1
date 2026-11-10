$ErrorActionPreference = 'Stop'

$raizImplementacao = Split-Path -Parent $PSScriptRoot
$pastaBuild = Join-Path $PSScriptRoot 'build'
$fontes = @(
    (Join-Path $PSScriptRoot 'MiniSO.java')
)

foreach ($modulo in @('Modulo1', 'Modulo2', 'Modulo3', 'Modulo4', 'Modulo5')) {
    $fontes += @(Get-ChildItem -LiteralPath (Join-Path $raizImplementacao $modulo) -Filter '*.java' | ForEach-Object FullName)
}

if (-not (Get-Command javac -ErrorAction SilentlyContinue)) {
    throw 'JDK não encontrado. Instale o Java Development Kit e adicione javac ao PATH.'
}

New-Item -ItemType Directory -Path $pastaBuild -Force | Out-Null
& javac -encoding UTF-8 -d $pastaBuild $fontes
if ($LASTEXITCODE -ne 0) {
    throw 'Não foi possível compilar o Mini-SO.'
}

& java -cp $pastaBuild miniso.menu.MiniSO "--raiz=$raizImplementacao"
