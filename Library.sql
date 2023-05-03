CREATE DATABASE LibraryManagement;
GO

USE LibraryManagement;
GO

CREATE TABLE Student
(
		studentId VARCHAR(20) NOT NULL,
		name NVARCHAR(100) NOT NULL,
		className NVARCHAR(100),
		faculty NVARCHAR(100),
		dateOfBirth DATE,
		gender NVARCHAR(10),
		address NVARCHAR(255),
		phoneNumber NVARCHAR(20),
		CONSTRAINT pk_Student PRIMARY KEY (studentId)
);
GO
CREATE TABLE Category
(
		categoryId INT IDENTITY(1,1) NOT NULL,
		categoryName NVARCHAR(255) NOT NULL,
		CONSTRAINT pk_Category PRIMARY KEY (categoryId)
);
GO
CREATE TABLE Book
(
		bookId VARCHAR(20) NOT NULL,
		bookName NVARCHAR(255) NOT NULL,
		categoryId INT,
		author NVARCHAR(100),
		publisher NVARCHAR(255),
		price FLOAT,
		amount SMALLINT,
		CONSTRAINT pk_Book PRIMARY KEY (bookId),
		CONSTRAINT fk_Book_categoryId
			   FOREIGN KEY (categoryId)
			   REFERENCES Category(categoryId)
);
GO

CREATE TABLE Admin
(
		adminId INT IDENTITY(1,1) NOT NULL,
		name NVARCHAR(100) NOT NULL,
		dateOfBirth DATE,
		gender NVARCHAR(10),
		address NVARCHAR(255),
		phoneNumber NVARCHAR(20),
		accountName NVARCHAR(100) NOT NULL,
		password NVARCHAR(100) NOT NULL,
		CONSTRAINT pk_Admin PRIMARY KEY (adminId)
);
GO

CREATE TABLE LendingSlip
(
		lendingSlipId INT IDENTITY(1,1) NOT NULL,
		adminId INT,
		studentId VARCHAR(20),
		lendDate DATE,
		dueDate DATE,
		returnDate DATE,
		status NVARCHAR(20),
		CONSTRAINT pk_LendingSlip PRIMARY KEY (lendingSlipId),
		CONSTRAINT fk_lendingSlipId_adminId
			   FOREIGN KEY (adminId)
			   REFERENCES Admin(adminId),
		CONSTRAINT fk_lendingSlipId_studentId
			   FOREIGN KEY (studentId)
			   REFERENCES Student(studentId)

);
GO

CREATE TABLE LendingSlipDetail
(
		lendingSlipId INT NOT NULL,
		bookId VARCHAR(20) NOT NULL,
		amount SMALLINT,
		CONSTRAINT pk_LendingSlipDetail PRIMARY KEY (lendingSlipId,bookId),
		CONSTRAINT fk_LendingSlipDetail_lendingSlipId
			   FOREIGN KEY (lendingSlipId)
			   REFERENCES LendingSlip(lendingSlipId),
		CONSTRAINT fk_LendingSlipDetail_bookId
			   FOREIGN KEY (bookId)
			   REFERENCES Book(bookId)
);


INSERT INTO Category (categoryName) 
VALUES
		(N'Giáo trình'),
		(N'Sách tham khảo'),
		(N'Sách khoa học'),
		(N'Sách chuyên ngành'),
		(N'Sách ngoại ngữ'),
		(N'Sách kinh tế'),
		(N'Sách lịch sử và xã hội'),
		(N'Sách công nghệ thông tin');

INSERT INTO Book (bookId, bookName, categoryId, author, publisher, price, amount)
VALUES
		('GT0101', N'Giáo trình môn Toán cao cấp', 1, N'Phạm Văn Hùng', N'Đại học Quốc gia Hà Nội', 200000, 10),
		('GT0102', N'Giáo trình môn Kỹ thuật lập trình', 1, N'Nguyễn Đức Thắng', N'Nhà xuất bản Đại học Quốc gia TP.HCM', 150000, 20),
		('STK0101', N'Từ điển Anh - Việt', 2, N'Trần Quang Đức', N'Nhà xuất bản Lao động', 50000, 30),
		('SCN0101', N'Bài tập đại số tuyến tính', 4, N'Lê Văn Thịnh', N'Đại học Quốc gia Hà Nội', 80000, 25),
		('SNN0101', N'Học tiếng Nhật', 5, N'Nguyễn Thị Lan', N'Nhà xuất bản Giáo dục', 70000, 40),
		('SKT0101', N'Kinh tế học đại cương', 6, N'Nguyễn Đức Thắng', N'Nhà xuất bản Đại học Quốc gia TP.HCM', 90000, 20),
		('SCNTT01', N'Lập trình Web với PHP', 8, N'Nguyễn Văn An', N'Nhà xuất bản Khoa học Tự nhiên và Công nghệ', 80000, 15),
		('GT0103', N'Giáo trình môn đại số tuyến tính', 1, N'Vũ Xuân Hùng', N'Nhà xuất bản Đại học Quốc gia Hà Nội', 130000, 8),
		('STK0201', N'Từ điển Nhật - Việt', 2, N'Phạm Thị Hương', N'Nhà xuất bản Giáo dục', 70000, 25),
		('SKT0102', N'Tiền tệ và ngân hàng', 6, N'Nguyễn Xuân Nghĩa', N'Nhà xuất bản Thống kê', 90000, 12),
		('SKT0103', N'Kinh tế học hài hước', 6, N'Nguyễn Minh Đức', N'Nhà xuất bản Tri thức', 110000, 8),
		('SKT0104', N'Tiền và tâm lý', 6, N'Đỗ Hải Phong', N'Nhà xuất bản Tổng hợp TPHCM', 90000, 12),
		('SKT0105', N'Tổ chức và quản lý sản xuất', 6, N'Phạm Thanh Hà', N'Nhà xuất bản Thống kê', 120000, 8),
		('SLCH0101', N'Lịch sử văn hóa phương Đông', 7, N'Nguyễn Khắc Thuần', N'Nhà xuất bản Đại học Quốc gia Hà Nội', 150000, 5),
		('SCNTT02', N'Tin học đại cương', 8, N'Lê Thành Sách', N'Nhà xuất bản Giáo dục', 85000, 20),
		('SCNTT03', N'Nhập môn lập trình Java', 8, N'Nguyễn Thành Nam', N'Nhà xuất bản Đại học Khoa học Tự nhiên', 130000, 15),
		('GT0104', N'Phương trình vi phân thường', 1, N'Nguyễn Văn Dũng', N'Nhà xuất bản Giáo dục', 110000, 10),
		('SNN0201', N'Học tiếng Anh cho người mới bắt đầu', 5, N'Nguyễn Thị Hải', N'Nhà xuất bản Thanh Niên', 75000, 25),
		('SKH0101', N'Tốc độ ánh sáng', 3, N'Phan Bảo Ngọc', N'Nhà xuất bản Thế giới', 85000, 12),
		('SKH0108', N'Sinh học di truyền', 3, N'Phan Đình Khương', N'Nhà xuất bản Đại học Quốc gia TP.HCM', 130000, 8),
		('SKH0109', N'Kỹ thuật đo lường và kiểm tra', 3, N'Nguyễn Thị Liên', N'Nhà xuất bản Tổng hợp TPHCM', 110000, 10),
		('SKH0110', N'Triết học Khoa học', 3, N'Hà Huyền Châu', N'Nhà xuất bản Chính trị Quốc gia', 95000, 15);


INSERT INTO Student (studentId, name, className, faculty, dateOfBirth, gender, address, phoneNumber)
VALUES
		('SV01', N'Nguyễn Hồng Hạnh', N'Công nghệ thông tin 5', N'Khoa Công nghệ thông tin', '2000-01-01', N'Nữ', N'Nam Định', '0353958240'),
		('SV02', N'Đỗ Duy Nam', N'Công nghệ thông tin 3', N'Khoa Công nghệ thông tin', '2000-09-09', N'Nam', N'Hà Nội', '0123246789'),
		('SV03', N'Nguyễn Văn Kiên', N'Kế toán 1', N'Khoa Kinh tế', '2000-10-10', N'Nữ', N'Hải Phòng', '0343456789'),
		('SV04', N'Đặng Văn Tùng', N'Công trình 1', N'Khoa Công trình', '2001-08-08', N'Nam', N'Hưng Yên', '0849273813'),
		('SV05', N'Phạm Thị Hồng', N'Kế toán 2', N'Khoa Kinh tế', '2001-03-16', N'Nữ', N'Hà Nội', '0987654321'),
		('SV06', N'Nguyễn Văn Minh', N'Công nghệ thông tin 2', N'Khoa Công nghệ thông tin', '2000-12-24', N'Nam', N'Bắc Ninh', '0359876543'),
		('SV07', N'Hoàng Thị Mai', N'Tài chính 1', N'Khoa Tài chính - Ngân hàng', '2001-02-14', N'Nữ', N'Thái Nguyên', '0976543210'),
		('SV08', N'Lê Văn Tùng', N'Quản trị kinh doanh 1', N'Khoa Quản trị kinh doanh', '2001-07-18', N'Nam', N'Bình Dương', '0934567891'),
		('SV09', N'Phạm Thị Anh', N'Luật 1', N'Khoa Luật', '2001-04-22', N'Nữ', N'Hải Dương', '0123456789'),
		('SV10', N'Nguyễn Văn Trọng', N'Công nghệ thực phẩm 1', N'Khoa Công nghệ thực phẩm', '2000-11-30', N'Nam', N'Đà Nẵng', '0987654321')

INSERT INTO Admin (name, dateOfBirth, gender, address, phoneNumber, accountName,password)
VALUES 
		(N'Nguyễn Văn An', '1990-01-01', N'Nam', N'Hà Nội', '0392038416', N'admin1','123456'),
		(N'Trần Thị Bình', '1980-05-05', N'Nữ', N'Hải Phòng', '0987654321', N'admin2','234567'),
		(N'Phạm Văn Cường', '1987-12-25', N'Nam', N'Thái Bình', '0969696969', N'admin3','345678');

INSERT INTO LendingSlip (adminId, studentId, lendDate, dueDate, returnDate, status)
VALUES
		(3, 'SV02', '2022-03-25', '2022-04-08 ', '2022-04-08',N'Đã trả'),
		(2, 'SV01', '2022-04-01', '2022-04-15 ', '2022-04-15', N'Đã trả'),
		(1, 'SV09', '2022-04-07', '2022-05-21 ', '2022-05-21 ', N'Đã trả'),
		(2, 'SV10', '2022-04-09', '2022-05-09 ', NULL, N'Quá hạn'),
		(1, 'SV02', '2022-05-13', '2022-05-27 ', '2022-05-27 ', N'Đã trả'),
		(2, 'SV03', '2022-05-15', '2022-05-29 ', '2022-05-29', N'Đã trả'),
		(3, 'SV10', '2023-02-17', '2023-03-31 ', NULL, N'Quá hạn'),
		(3, 'SV01', '2023-03-11', '2023-03-25 ', '2023-03-24 ', N'Đã trả'),
		(1, 'SV05', '2023-03-19', '2023-04-02 ', NULL, N'Quá hạn'),
		(1, 'SV06', '2023-04-01', '2023-04-25 ', '2023-04-25', N'Đã trả'),
		(2, 'SV07', '2023-04-03', '2023-05-17 ', NULL, N'Đang mượn'),
		(3, 'SV08', '2023-04-05', '2023-04-20 ', '2023-04-21 ', N'Đã trả'),
		(1, 'SV03', '2023-04-10', '2023-05-09 ',NULL, N'Đang mượn'),
		(3, 'SV04', '2023-04-18', '2023-05-02 ',NULL, N'Đang mượn'),
		(2, 'SV05', '2023-04-22', '2023-05-06 ', NULL, N'Đang mượn');
		
INSERT INTO LendingSlipDetail (lendingSlipId, bookId, amount)
VALUES
	   (1, 'SCN0101', 1),
		(1, 'STK0101', 2),
		(1, 'GT0101', 1),
		(2, 'SNN0101', 1),
		(2, 'SKT0101', 3),
		(2, 'GT0102', 1),
		(3, 'SCNTT01', 2),
		(3, 'GT0103', 1),
		(3, 'SKH0101',1),
		(4, 'STK0101', 1),
		(4, 'SCN0101', 2),
		(4, 'STK0201', 2),
		(5, 'SNN0101', 3),
		(5, 'SCNTT01', 1),
		(5, 'SKH0110', 1);

INSERT INTO LendingSlipDetail (lendingSlipId, bookId, amount)
VALUES
	   (6, 'SCN0101', 1),
		(7, 'STK0101', 2),
		(8, 'SNN0101', 1),
		(9, 'SKT0101', 3),
		(10, 'SCNTT01', 2),
		(11, 'GT0103', 1),
		(12, 'STK0101', 1),
		(13, 'SCN0101', 2),
		(14, 'SNN0101', 3),
		(15, 'SCNTT01', 1);
		