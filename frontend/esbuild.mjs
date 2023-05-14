import esbuild from "esbuild";
import {sassPlugin} from "esbuild-sass-plugin";
import {globPlugin} from 'esbuild-plugin-glob';

await esbuild.build({
	entryPoints: ["src/main/scss/*.scss"],
	entryNames: '[dir]/[name].min',
	outdir: "build/dist",
	bundle: true,
	minify: true,
	sourcemap: true,
	external: ['CaskaydiaCove.ttf'],
	plugins: [
		globPlugin(),
		sassPlugin()
	]
})

await esbuild.build({
	entryPoints: ["src/main/ts/*.ts"],
	entryNames: '[dir]/[name].min',
	outdir: "build/dist",
	bundle: true,
	minify: true,
	sourcemap: true,
	plugins: [
		globPlugin()
	]
})
