import gulp, {parallel, series} from 'gulp';
import rename from 'gulp-rename';
import ts from 'gulp-typescript';
import gulpSass from 'gulp-sass';
//import sourcemaps from 'gulp-sourcemaps';
import cleanCSS from 'gulp-clean-css';
import uglify from 'gulp-uglify';

import dartSass from 'sass';
import del from 'del';

const sass = gulpSass(dartSass);

const input = "src/main";
const output = 'build/dist';

function clean() {
	return del(`${output}/**`);
}

function scssTranspile() {
	return gulp.src(`${input}/scss/**/*.scss`)
		//.pipe(sourcemaps.init())
		.pipe(sass.sync().on('error', sass.logError))
		//.pipe(sourcemaps.write())
		.pipe(gulp.dest(output));
}

function cssMinify() {
	return gulp.src([`${output}/**/*.css`, `!${output}/**/*.min.css`])
		.pipe(cleanCSS())
		.pipe(rename(function (path) {
			path.basename += ".min";
		}))
		.pipe(gulp.dest(output));
}

function tsTranspile() {
	return gulp.src(`${input}/ts/**/*.ts`)
		//.pipe(sourcemaps.init())
		.pipe(ts())
		//.pipe(sourcemaps.write())
		.pipe(gulp.dest(output));
}

function jsMinify() {
	return gulp.src([`${output}/**/*.js`, `!${output}/**/*.min.js`])
		.pipe(uglify())
		.pipe(rename(function (path) {
			path.basename += ".min";
		}))
		.pipe(gulp.dest(output));
}

exports.clean = clean;
exports.build = series(
	parallel(scssTranspile, tsTranspile),
	parallel(cssMinify, jsMinify)
);
