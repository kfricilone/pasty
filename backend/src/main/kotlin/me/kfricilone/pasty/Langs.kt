/*
 * Copyright (c)  Kyle Fricilone (https://kfricilone.me)
 *
 * Permission to use, copy, modify, and/or distribute this software for any
 * purpose with or without fee is hereby granted, provided that the above
 * copyright notice and this permission notice appear in all copies.
 *
 * THE SOFTWARE IS PROVIDED "AS IS" AND THE AUTHOR DISCLAIMS ALL WARRANTIES
 * WITH REGARD TO THIS SOFTWARE INCLUDING ALL IMPLIED WARRANTIES OF
 * MERCHANTABILITY AND FITNESS. IN NO EVENT SHALL THE AUTHOR BE LIABLE FOR
 * ANY SPECIAL, DIRECT, INDIRECT, OR CONSEQUENTIAL DAMAGES OR ANY DAMAGES
 * WHATSOEVER RESULTING FROM LOSS OF USE, DATA OR PROFITS, WHETHER IN AN
 * ACTION OF CONTRACT, NEGLIGENCE OR OTHER TORTIOUS ACTION, ARISING OUT OF
 * OR IN CONNECTION WITH THE USE OR PERFORMANCE OF THIS SOFTWARE.
 */

package me.kfricilone.pasty

/**
 * Created by Kyle Fricilone on Oct 28, 2021.
 */
public enum class Langs(
    public val langs: Map<String, String>
) {

    MIN(
        mapOf(
            "antlr4" to "ANTLR4",
            "c" to "C",
            "csharp" to "C#",
            "css" to "CSS",
            "cpp" to "C++",
            "diff" to "Diff",
            "docker" to "Docker",
            "java" to "Java",
            "javascript" to "JavaScript",
            "json" to "JSON",
            "kotlin" to "Kotlin",
            "markdown" to "Markdown",
            "markup" to "Markup",
            "protobuf" to "Protocol Buffers",
            "python" to "Python",
            "regex" to "Regex",
            "scss" to "Sass (Scss)",
            "typescript" to "TypeScript",
            "yaml" to "YAML"
        )
    ),
    ALL(
        mapOf(
            "markup" to "Markup",
            "css" to "CSS",
            "clike" to "C-like",
            "javascript" to "JavaScript",
            "abap" to "ABAP",
            "abnf" to "ABNF",
            "actionscript" to "ActionScript",
            "ada" to "Ada",
            "agda" to "Agda",
            "al" to "AL",
            "antlr4" to "ANTLR4",
            "apacheconf" to "Apache Configuration",
            "apex" to "Apex",
            "apl" to "APL",
            "applescript" to "AppleScript",
            "aql" to "AQL",
            "arduino" to "Arduino",
            "arff" to "ARFF",
            "asciidoc" to "AsciiDoc",
            "aspnet" to "ASP.NET (C#)",
            "asm6502" to "6502 Assembly",
            "asmatmel" to "Atmel AVR Assembly",
            "autohotkey" to "AutoHotkey",
            "autoit" to "AutoIt",
            "avisynth" to "AviSynth",
            "avro-idl" to "Avro IDL",
            "bash" to "Bash",
            "basic" to "BASIC",
            "batch" to "Batch",
            "bbcode" to "BBcode",
            "bicep" to "Bicep",
            "birb" to "Birb",
            "bison" to "Bison",
            "bnf" to "BNF",
            "brainfuck" to "Brainfuck",
            "brightscript" to "BrightScript",
            "bro" to "Bro",
            "bsl" to "BSL (1C:Enterprise)",
            "c" to "C",
            "csharp" to "C#",
            "cpp" to "C++",
            "cfscript" to "CFScript",
            "chaiscript" to "ChaiScript",
            "cil" to "CIL",
            "clojure" to "Clojure",
            "cmake" to "CMake",
            "cobol" to "COBOL",
            "coffeescript" to "CoffeeScript",
            "concurnas" to "Concurnas",
            "csp" to "Content-Security-Policy",
            "coq" to "Coq",
            "crystal" to "Crystal",
            "css-extras" to "CSS Extras",
            "csv" to "CSV",
            "cypher" to "Cypher",
            "d" to "D",
            "dart" to "Dart",
            "dataweave" to "DataWeave",
            "dax" to "DAX",
            "dhall" to "Dhall",
            "diff" to "Diff",
            "django" to "Django/Jinja2",
            "dns-zone-file" to "DNS zone file",
            "docker" to "Docker",
            "dot" to "DOT (Graphviz)",
            "ebnf" to "EBNF",
            "editorconfig" to "EditorConfig",
            "eiffel" to "Eiffel",
            "ejs" to "EJS",
            "elixir" to "Elixir",
            "elm" to "Elm",
            "etlua" to "Embedded Lua templating",
            "erb" to "ERB",
            "erlang" to "Erlang",
            "excel-formula" to "Excel Formula",
            "fsharp" to "F#",
            "factor" to "Factor",
            "false" to "False",
            "firestore-security-rules" to "Firestore security rules",
            "flow" to "Flow",
            "fortran" to "Fortran",
            "ftl" to "FreeMarker Template Language",
            "gml" to "GameMaker Language",
            "gap" to "GAP (CAS)",
            "gcode" to "G-code",
            "gdscript" to "GDScript",
            "gedcom" to "GEDCOM",
            "gherkin" to "Gherkin",
            "git" to "Git",
            "glsl" to "GLSL",
            "gn" to "GN",
            "go" to "Go",
            "graphql" to "GraphQL",
            "groovy" to "Groovy",
            "haml" to "Haml",
            "handlebars" to "Handlebars",
            "haskell" to "Haskell",
            "haxe" to "Haxe",
            "hcl" to "HCL",
            "hlsl" to "HLSL",
            "hoon" to "Hoon",
            "http" to "HTTP",
            "hpkp" to "HTTP Public-Key-Pins",
            "hsts" to "HTTP Strict-Transport-Security",
            "ichigojam" to "IchigoJam",
            "icon" to "Icon",
            "icu-message-format" to "ICU Message Format",
            "idris" to "Idris",
            "ignore" to ".ignore",
            "inform7" to "Inform 7",
            "ini" to "Ini",
            "io" to "Io",
            "j" to "J",
            "java" to "Java",
            "javadoc" to "JavaDoc",
            "javadoclike" to "JavaDoc-like",
            "javastacktrace" to "Java stack trace",
            "jexl" to "Jexl",
            "jolie" to "Jolie",
            "jq" to "JQ",
            "jsdoc" to "JSDoc",
            "js-extras" to "JS Extras",
            "json" to "JSON",
            "json5" to "JSON5",
            "jsonp" to "JSONP",
            "jsstacktrace" to "JS stack trace",
            "js-templates" to "JS Templates",
            "julia" to "Julia",
            "keepalived" to "Keepalived Configure",
            "keyman" to "Keyman",
            "kotlin" to "Kotlin",
            "kumir" to "KuMir (КуМир)",
            "kusto" to "Kusto",
            "latex" to "LaTeX",
            "latte" to "Latte",
            "less" to "Less",
            "lilypond" to "LilyPond",
            "liquid" to "Liquid",
            "lisp" to "Lisp",
            "livescript" to "LiveScript",
            "llvm" to "LLVM IR",
            "log" to "Log file",
            "lolcode" to "LOLCODE",
            "lua" to "Lua",
            "magma" to "Magma (CAS)",
            "makefile" to "Makefile",
            "markdown" to "Markdown",
            "markup-templating" to "Markup templating",
            "matlab" to "MATLAB",
            "maxscript" to "MAXScript",
            "mel" to "MEL",
            "mermaid" to "Mermaid",
            "mizar" to "Mizar",
            "mongodb" to "MongoDB",
            "monkey" to "Monkey",
            "moonscript" to "MoonScript",
            "n1ql" to "N1QL",
            "n4js" to "N4JS",
            "nand2tetris-hdl" to "Nand To Tetris HDL",
            "naniscript" to "Naninovel Script",
            "nasm" to "NASM",
            "neon" to "NEON",
            "nevod" to "Nevod",
            "nginx" to "nginx",
            "nim" to "Nim",
            "nix" to "Nix",
            "nsis" to "NSIS",
            "objectivec" to "Objective-C",
            "ocaml" to "OCaml",
            "opencl" to "OpenCL",
            "openqasm" to "OpenQasm",
            "oz" to "Oz",
            "parigp" to "PARI/GP",
            "parser" to "Parser",
            "pascal" to "Pascal",
            "pascaligo" to "Pascaligo",
            "psl" to "PATROL Scripting Language",
            "pcaxis" to "PC-Axis",
            "peoplecode" to "PeopleCode",
            "perl" to "Perl",
            "php" to "PHP",
            "phpdoc" to "PHPDoc",
            "php-extras" to "PHP Extras",
            "plsql" to "PL/SQL",
            "powerquery" to "PowerQuery",
            "powershell" to "PowerShell",
            "processing" to "Processing",
            "prolog" to "Prolog",
            "promql" to "PromQL",
            "properties" to ".properties",
            "protobuf" to "Protocol Buffers",
            "pug" to "Pug",
            "puppet" to "Puppet",
            "pure" to "Pure",
            "purebasic" to "PureBasic",
            "purescript" to "PureScript",
            "python" to "Python",
            "qsharp" to "Q#",
            "q" to "Q (kdb+ database)",
            "qml" to "QML",
            "qore" to "Qore",
            "r" to "R",
            "racket" to "Racket",
            "cshtml" to "Razor C#",
            "jsx" to "React JSX",
            "tsx" to "React TSX",
            "reason" to "Reason",
            "regex" to "Regex",
            "rego" to "Rego",
            "renpy" to "Ren'py",
            "rest" to "reST (reStructuredText)",
            "rip" to "Rip",
            "roboconf" to "Roboconf",
            "robotframework" to "Robot Framework",
            "ruby" to "Ruby",
            "rust" to "Rust",
            "sas" to "SAS",
            "sass" to "Sass (Sass)",
            "scss" to "Sass (Scss)",
            "scala" to "Scala",
            "scheme" to "Scheme",
            "shell-session" to "Shell session",
            "smali" to "Smali",
            "smalltalk" to "Smalltalk",
            "smarty" to "Smarty",
            "sml" to "SML",
            "solidity" to "Solidity (Ethereum)",
            "solution-file" to "Solution file",
            "soy" to "Soy (Closure Template)",
            "sparql" to "SPARQL",
            "splunk-spl" to "Splunk SPL",
            "sqf" to "SQF: Status Quo Function (Arma 3)",
            "sql" to "SQL",
            "squirrel" to "Squirrel",
            "stan" to "Stan",
            "iecst" to "Structured Text (IEC 61131-3)",
            "stylus" to "Stylus",
            "swift" to "Swift",
            "systemd" to "Systemd configuration file",
            "t4-templating" to "T4 templating",
            "t4-cs" to "T4 Text Templates (C#)",
            "t4-vb" to "T4 Text Templates (VB)",
            "tap" to "TAP",
            "tcl" to "Tcl",
            "tt2" to "Template Toolkit 2",
            "textile" to "Textile",
            "toml" to "TOML",
            "tremor" to "Tremor",
            "turtle" to "Turtle",
            "twig" to "Twig",
            "typescript" to "TypeScript",
            "typoscript" to "TypoScript",
            "unrealscript" to "UnrealScript",
            "uri" to "URI",
            "v" to "V",
            "vala" to "Vala",
            "vbnet" to "VB.Net",
            "velocity" to "Velocity",
            "verilog" to "Verilog",
            "vhdl" to "VHDL",
            "vim" to "vim",
            "visual-basic" to "Visual Basic",
            "warpscript" to "WarpScript",
            "wasm" to "WebAssembly",
            "web-idl" to "Web IDL",
            "wiki" to "Wiki markup",
            "wolfram" to "Wolfram language",
            "wren" to "Wren",
            "xeora" to "Xeora",
            "xml-doc" to "XML doc (.net)",
            "xojo" to "Xojo (REALbasic)",
            "xquery" to "XQuery",
            "yaml" to "YAML",
            "yang" to "YANG",
            "zig" to "Zig"
        )
    );

    public companion object {
        public const val Key: String = "LANGS"
        public val Default: String = MIN.name
    }
}